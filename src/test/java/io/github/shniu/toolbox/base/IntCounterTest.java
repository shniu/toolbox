package io.github.shniu.toolbox.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2020/11/4 09
 */
class IntCounterTest {

    @Test
    void test_intCounter() {
        IntCounter intCounter = new IntCounter();

        assertEquals(0, intCounter.get());
        assertEquals(0, intCounter.getAndIncrement());
        assertEquals(2, intCounter.incrementAndGet());
        assertEquals(4, intCounter.incrementAndGet(2));
        assertEquals(4, intCounter.getAndIncrement(2));
        assertEquals(6, intCounter.get());
    }

}