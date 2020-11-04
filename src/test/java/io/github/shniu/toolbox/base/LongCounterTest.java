package io.github.shniu.toolbox.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2020/11/4 09
 */
class LongCounterTest {

    @Test
    void test_longCounter() {
        LongCounter longCounter = new LongCounter();

        assertEquals(0, longCounter.get());

        assertEquals(1, longCounter.incrementAndGet());
        assertEquals(1, longCounter.getAndIncrement());
        assertEquals(2, longCounter.getAndIncrement());
        assertEquals(4, longCounter.incrementAndGet());
        assertEquals(6, longCounter.incrementAndGet(2));
        assertEquals(6, longCounter.getAndIncrement(2));
        assertEquals(8, longCounter.get());
    }

}