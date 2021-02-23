package io.github.shniu.toolbox.collections;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * @author niushaohan
 * @date 2021/2/23 16
 */
public class CoalescingSynchronizedBuffer<K, V> implements CoalescingBuffer<K, V> {
    private final int capacity;
    private final LinkedHashMap<K, V> linkedHashMap;

    public CoalescingSynchronizedBuffer(int capacity) {
        this.capacity = capacity;
        this.linkedHashMap = new LinkedHashMap<>(capacity);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public synchronized boolean offer(K key, V value) {
        if (linkedHashMap.containsKey(key)) {
            linkedHashMap.put(key, value);
            return true;
        }

        if (linkedHashMap.size() == capacity) {
            return false;
        }

        linkedHashMap.put(key, value);
        return true;
    }

    @Override
    public synchronized boolean offer(V value) {
        return false;
    }

    @Override
    public synchronized int poll(Collection<? super V> bucket) {
        int size = linkedHashMap.size();
        for (V val : linkedHashMap.values()) {
            bucket.add(val);
        }

        linkedHashMap.clear();
        return size;
    }

    @Override
    public synchronized int poll(Collection<? super V> bucket, int maxEntries) {
        return 0;
    }
}
