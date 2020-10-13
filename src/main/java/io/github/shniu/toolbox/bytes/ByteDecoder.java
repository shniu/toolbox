package io.github.shniu.toolbox.bytes;

/**
 * @author niushaohan
 * @date 2020/10/13 13
 */
public interface ByteDecoder {

    /**
     * Byte array container for Decoder.
     */
    byte[] getBytes();

    int length();
    int position();

    /**
     * Read the byte at position i.
     */
    byte read(int i);

    /**
     * Read the byte at current position, then move the position to next.
     */
    byte read();

    /**
     * Read two consecutive bytes from the current position and convert them to Integer.
     */
    int readB2();

    /**
     * Read three consecutive bytes from the current position and convert them to Integer.
     */
    int readB3();

    /**
     * Read four consecutive bytes from the current position and convert them to Long.
     */
    long readB4();

    /**
     * Read four consecutive bytes from the current position and convert them to Integer.
     */
    int readInt();

    /**
     * Read eight consecutive bytes from the current position and convert them to Integer.
     */
    long readLong();
}
