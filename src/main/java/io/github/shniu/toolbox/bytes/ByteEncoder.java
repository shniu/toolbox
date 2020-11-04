package io.github.shniu.toolbox.bytes;

/**
 * @author niushaohan
 * @date 2020/10/13 13
 */
public interface ByteEncoder {

    void write();
    void writeB2();
    void writeB3();
    void writeInt();
    void writeB4();
    void writeLong();

    void writeFloat();
    void writeDouble();

    void writeLength();
    void writeWithLength();
    void writeWithNull();
}
