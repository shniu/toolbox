package io.github.shniu.toolbox.bytes;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2020/10/13 13
 */
class DefaultByteDecoderTest {

    @Test
    public void test_read() {
        byte[] bytes = new byte[]{10, 23, 45, 88, 66};

        ByteDecoder byteDecoder = new DefaultByteDecoder(bytes);

        assertEquals(5, byteDecoder.length());
        assertEquals(0, byteDecoder.position());

        assertEquals(23, byteDecoder.read(1));

        byte b1 = byteDecoder.read();
        assertEquals(10, b1);

        int b2 = byteDecoder.readB2();
        String high = Integer.toBinaryString(45);
        String low = Integer.toBinaryString(23);
        String expected = high + low;
        System.out.println(high + " " + low + " " + expected);
        Integer expectedB2 = Integer.valueOf("0010110100010111", 2);
        System.out.println(expectedB2);
        System.out.println(b2);
        assertEquals(expectedB2, b2);

        assertThrows(RuntimeException.class, byteDecoder::readB3);
    }

    @Test
    public void testByteBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        byteBuffer.array();

        ByteBuffer directBuffer = ByteBuffer.allocateDirect(256);
    }
}