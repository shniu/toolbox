package io.github.shniu.toolbox.collections;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * The Coalescing Ring Buffer is the first component of the LMAX Collections Library we are open-sourcing today.
 * It is a component that we have written in Java to efficiently buffer messages between a producer and a consumer
 * thread where only the latest value for a given topic is of interest.
 * All other messages can be discarded immediately.
 *
 * @author niushaohan
 * @date 2021/2/4 22
 */
public class CoalescingRingBuffer<K, V> implements CoalescingBuffer<K, V> {
    private final AtomicLong nextWrite = new AtomicLong(1); // the next write index
    private long lastCleaned = 0; // the last index that was nulled out by the producer
    private final AtomicLong rejectionCount = new AtomicLong(0);
    private final K[] keys;
    private final AtomicReferenceArray<V> values;

    @SuppressWarnings("unchecked")
    private final K nonCollapsibleKey = (K) new Object();
    private final int mask;
    private final int capacity;

    private volatile long firstWrite = 1; // the oldest slot that is safe to write to
    private final AtomicLong lastRead = new AtomicLong(0); // the newest slot that it is safe to overwrite

    @SuppressWarnings("unchecked")
    public CoalescingRingBuffer(int capacity) {
        // 确保 Buffer 的容量是 2 的幂次方，好处是在计算 slot 时可以使用位运算，会更高效
        this.capacity = nextPowerOfTwo(capacity);
        // 掩码，value & mask
        this.mask = this.capacity - 1;

        this.keys = (K[]) new Object[this.capacity];
        this.values = new AtomicReferenceArray<V>(this.capacity);
    }

    private int nextPowerOfTwo(int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }

    @Override
    public int size() {

        // loop until you get a consistent read of both volatile indices
        while (true) {
            long lastReadBefore = lastRead.get();
            long currentNextWrite = this.nextWrite.get();
            long lastReadAfter = lastRead.get();

            if (lastReadBefore == lastReadAfter) {
                return (int) (currentNextWrite - lastReadBefore) - 1;
            }
        }
    }

    @Override
    public int capacity() {
        return capacity;
    }

    public long rejectionCount() {
        return rejectionCount.get();
    }

    public long nextWrite() {
        return nextWrite.get();
    }

    public long firstWrite() {
        return firstWrite;
    }

    @Override
    public boolean isEmpty() {
        return firstWrite == nextWrite.get();
    }

    @Override
    public boolean isFull() {
        return size() == capacity;
    }

    @Override
    public boolean offer(K key, V value) {
        long nextWrite = this.nextWrite.get();

        for (long updatePosition = firstWrite; updatePosition < nextWrite; updatePosition++) {
            int index = mask(updatePosition);

            if (key.equals(keys[index])) {
                values.set(index, value);

                if (updatePosition >= firstWrite) {  // check that the reader has not read beyond our update point yet
                    return true;
                } else {
                    break;
                }
            }
        }

        return add(key, value);
    }

    @Override
    public boolean offer(V value) {
        return add(nonCollapsibleKey, value);
    }

    private boolean add(K key, V value) {
        if (isFull()) {
            rejectionCount.lazySet(rejectionCount.get() + 1);
            return false;
        }

        cleanUp();
        store(key, value);
        return true;
    }

    private void cleanUp() {
        long lastRead = this.lastRead.get();

        if (lastRead == lastCleaned) {
            return;
        }

        while (lastCleaned < lastRead) {
            int index = mask(++lastCleaned);
            keys[index] = null;
            values.lazySet(index, null);
        }
    }

    private void store(K key, V value) {
        long nextWrite = this.nextWrite.get();
        int index = mask(nextWrite);

        keys[index] = key;
        values.set(index, value);

        this.nextWrite.lazySet(nextWrite + 1);
    }

    @Override
    public int poll(Collection<? super V> bucket) {
        return fill(bucket, nextWrite.get());
    }

    @Override
    public int poll(Collection<? super V> bucket, int maxItems) {
        long claimUpTo = Math.min(firstWrite + maxItems, nextWrite.get());
        return fill(bucket, claimUpTo);
    }

    private int fill(Collection<? super V> bucket, long claimUpTo) {
        firstWrite = claimUpTo;
        long lastRead = this.lastRead.get();

        for (long readIndex = lastRead + 1; readIndex < claimUpTo; readIndex++) {
            int index = mask(readIndex);
            bucket.add(values.get(index));
            values.set(index, null);
        }

        this.lastRead.lazySet(claimUpTo - 1);
        return (int) (claimUpTo - lastRead - 1);
    }

    private int mask(long value) {
        return ((int) value) & mask;
    }
}
