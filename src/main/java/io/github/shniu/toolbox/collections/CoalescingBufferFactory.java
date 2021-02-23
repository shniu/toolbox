package io.github.shniu.toolbox.collections;

/**
 * @author niushaohan
 * @date 2021/2/23 15
 */
public final class CoalescingBufferFactory {

    public static <K, V> CoalescingBuffer<K, V> create(int capacity) {
        // return new CoalescingRingBuffer<>(capacity);
        return new CoalescingSynchronizedBuffer<K, V>(capacity);
    }
}
