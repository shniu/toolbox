package io.github.shniu.toolbox.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2021/2/7 11
 */
class CoalescingBufferTest {
    private final static MarketSnapshot VOD_1 = MarketSnapshot.create(1, 3, 4);
    private final static MarketSnapshot VOD_2 = MarketSnapshot.create(1, 5, 6);
    private final static MarketSnapshot BP = MarketSnapshot.create(2, 7, 8);

    CoalescingBuffer<Long, MarketSnapshot> buffer;

    CoalescingBuffer<Long, MarketSnapshot> createBuffer(int capacity) {
        return new CoalescingRingBuffer<>(capacity);
    }

    // 测试 capacity
    @Test
    void shouldCorrectlyIncreaseTheCapacityToTheNextHigherPowerOfTwo() {
        checkCapacity(1024, createBuffer(1023));
        checkCapacity(1024, createBuffer(1024));
        checkCapacity(2048, createBuffer(1025));
    }

    void checkCapacity(int capacity, CoalescingBuffer<Long, MarketSnapshot> buffer) {
        assertEquals(capacity, buffer.capacity());

        for (int i = 0; i < capacity; i++) {
            boolean success = buffer.offer(MarketSnapshot.create(i, i, i));
            assertTrue(success);
        }
    }

    // 测试 size
    @Test
    void shouldCorrectlyReportSize() {
        Collection<MarketSnapshot> snapshots = new ArrayList<>();

        buffer = createBuffer(2);
        assertEquals(0, buffer.size());
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());

        buffer.offer(BP);
        assertEquals(1, buffer.size());
        assertFalse(buffer.isEmpty());
        assertFalse(buffer.isFull());

        buffer.offer(VOD_1.getInstrumentId(), VOD_1);
        assertEquals(2, buffer.size());
        assertFalse(buffer.isEmpty());
        assertTrue(buffer.isFull());

        buffer.poll(snapshots, 1);
        assertEquals(1, buffer.size());
        assertFalse(buffer.isEmpty());
        assertFalse(buffer.isFull());

        buffer.poll(snapshots, 1);
        assertEquals(0, buffer.size());
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    void shouldRejectValuesWithoutKeysWhenFull() {
        buffer = createBuffer(2);
        buffer.offer(BP);
        buffer.offer(BP);

        assertFalse(buffer.offer(BP));
        assertEquals(2, buffer.size());
    }

    @Test
    void shouldRejectNewKeysWhenFull() {
        buffer = createBuffer(2);

        buffer.offer(1L, BP);
        buffer.offer(2L, VOD_1);

        assertFalse(buffer.offer(4L, VOD_2));
        assertEquals(2, buffer.size());
    }

    @Test
    void shouldAcceptExistingKeysWhenFull() {
        buffer = createBuffer(2);
        buffer.offer(1L, BP);
        buffer.offer(2L, BP);

        assertTrue(buffer.offer(2L, BP));
        assertEquals(2, buffer.size());
    }
}