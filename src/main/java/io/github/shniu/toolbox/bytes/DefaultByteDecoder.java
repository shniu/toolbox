package io.github.shniu.toolbox.bytes;

/**
 * @author niushaohan
 * @date 2020/10/13 13
 */
public class DefaultByteDecoder implements ByteDecoder {
    private byte[] bytes;
    private final int length;
    private int position;

    public DefaultByteDecoder(final byte[] bytes) {
        this.bytes = bytes;
        this.length = bytes.length;
        this.position = 0;
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int position() {
        return position;
    }

    @Override
    public byte read(int i) {
        ensureCapacity(i);
        return bytes[i];
    }

    @Override
    public byte read() {
        ensureCapacity(position);
        return bytes[position++];
    }

    /**
     * e.g.:
     * 1. 00000100 & 11111111 -> 00000100
     * 2. 00001100 << 8 -> 00001100 00000000
     * 3. 00000000 00000100 | 00001100 00000000 -> 00001100 00000100
     */
    @Override
    public int readB2() {
        ensureCapacity(position, 2);

        final byte[] b = bytes;
        int p = b[position++] & 0xff;
        p |= (b[position++] & 0xff) << 8;
        return p;
    }

    @Override
    public int readB3() {
        ensureCapacity(position, 3);

        final byte[] b = bytes;
        int p = b[position++] & 0xff;
        p |= (b[position++] & 0xff) << 8;
        p |= (b[position++] & 0xff) << 16;
        return p;
    }

    @Override
    public long readB4() {
        ensureCapacity(position, 4);

        final byte[] b = bytes;
        long p = (long) (b[position++] & 0xff);
        p |= (long) (b[position++] & 0xff) << 8;
        p |= (long) (b[position++] & 0xff) << 16;
        p |= (long) (b[position++] & 0xff) << 24;
        return p;
    }

    @Override
    public int readInt() {
        ensureCapacity(position, 4);

        final byte[] b = bytes;
        int p = b[position++] & 0xff;
        p |= (b[position++] & 0xff) << 8;
        p |= (b[position++] & 0xff) << 16;
        p |= (b[position++] & 0xff) << 24;
        return p;
    }

    @Override
    public long readLong() {
        ensureCapacity(position, 8);

        final byte[] b = bytes;
        long p = (long) (b[position++] & 0xff);
        p |= (long) (b[position++] & 0xff) << 8;
        p |= (long) (b[position++] & 0xff) << 16;
        p |= (long) (b[position++] & 0xff) << 32;
        p |= (long) (b[position++] & 0xff) << 40;
        p |= (long) (b[position++] & 0xff) << 48;
        p |= (long) (b[position++] & 0xff) << 56;
        return p;
    }

    private void ensureCapacity(int i) {
        if (i >= length || i < 0) {
            throw new RuntimeException("Read position overflow.");
        }
    }

    /**
     * Make sure there's n bytes left.
     */
    private void ensureCapacity(int i, int remaining) {
        ensureCapacity(i);

        if (i + remaining > length) {
            throw new RuntimeException("Less than the number of bytes remaining.");
        }
    }
}
