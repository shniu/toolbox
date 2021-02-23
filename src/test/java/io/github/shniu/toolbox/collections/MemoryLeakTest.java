package io.github.shniu.toolbox.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author niushaohan
 * @date 2021/2/23 13
 */
public class MemoryLeakTest {

    private static class CountingKey {
        private final int id;
        private final AtomicInteger counter;

        public CountingKey(int id, AtomicInteger counter) {
            this.id = id;
            this.counter = counter;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            CountingKey other = (CountingKey) object;
            return this.id == other.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("counting key: " + this.id);
            counter.incrementAndGet();
            super.finalize();
        }
    }

    private static class CountingValue {
        private final AtomicInteger counter;
        private final int v;

        private CountingValue(int v, AtomicInteger counter) {
            this.counter = counter;
            this.v = v;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("counting value: " + this.v);
            counter.incrementAndGet();
            super.finalize();
        }
    }

    @Test
    void shouldNotHaveMemoryLeaks() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();

        CoalescingBuffer<CountingKey, CountingValue> buffer = new CoalescingRingBuffer<>(16);
        buffer.offer(new CountingValue(999, counter));

        buffer.offer(new CountingKey(1, counter), new CountingValue(1, counter));
        buffer.offer(new CountingKey(2, counter), new CountingValue(2, counter));
        buffer.offer(new CountingKey(1, counter), new CountingValue(11, counter));

        buffer.offer(new CountingValue(1000, counter));

        assertEquals(4, buffer.size());
        buffer.poll(new ArrayList<CountingValue>(), 1);
        buffer.poll(new ArrayList<CountingValue>());
        assertTrue(buffer.isEmpty());

        buffer.offer(null); // to trigger the clean
        for (int i = 0; i < 10; i++) {
            System.gc();
            Thread.sleep(100);
        }

        assertEquals(8, counter.get());
    }
}
