package io.github.shniu.toolbox.collections;

import java.util.Collection;

/**
 * @author niushaohan
 * @date 2021/2/4 20
 */
public interface CoalescingBuffer<K, V> {
    /**
     * The current size of buffer.
     *
     * @return buffer size
     */
    int size();

    /**
     * Capacity of buffer.
     *
     * @return the maximum size of buffer
     */
    int capacity();

    boolean isEmpty();
    boolean isFull();

    boolean offer(K key, V value);
    boolean offer(V value);

    /**
     * Add all available entries to the given bucket.
     * @param bucket the collection of entry to add
     *
     * @return the number of entries added
     */
    int poll(Collection<? super V> bucket);
    int poll(Collection<? super V> bucket, int maxEntries);
}
