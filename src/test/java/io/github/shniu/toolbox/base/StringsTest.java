package io.github.shniu.toolbox.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2020/11/3 19
 */
class StringsTest {

    @Test
    void test_stringNullOrEmpty() {
        assertTrue(Strings.isNullOrEmpty(null));
        assertTrue(Strings.isNullOrEmpty(""));
        assertFalse(Strings.isNullOrEmpty(" "));
        assertFalse(Strings.isNullOrEmpty(" aa"));
        assertFalse(Strings.isNullOrEmpty("1"));
    }

    @Test
    void test_stringNotEmpty() {
        assertTrue(Strings.isNotEmpty("aa"));
        assertTrue(Strings.isNotEmpty(" "));
        assertTrue(Strings.isNotEmpty("b "));
        assertFalse(Strings.isNotEmpty(""));
        assertFalse(Strings.isNotEmpty(null));
    }

    @Test
    void test_replace() {
        String replaced = Strings.replaceDash("abc-123-uuu");
        assertEquals("abc123uuu", replaced);

        replaced = Strings.replaceDash("-abc-123-uuu-");
        assertEquals("abc123uuu", replaced);
    }

}