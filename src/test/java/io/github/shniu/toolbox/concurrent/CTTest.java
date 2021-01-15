package io.github.shniu.toolbox.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2021/1/15 17
 */
class CTTest {

    @Test
    void test() {
        CT ct = new CT();
        ct.increment();
        assertEquals(1, ct.getCount());
    }
}