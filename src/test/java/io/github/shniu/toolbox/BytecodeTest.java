package io.github.shniu.toolbox;

/**
 * @author niushaohan
 * @date 2021/3/8 15
 */
public class BytecodeTest {
    private static final int MAX_COUNT = 8;
    private Reader reader;

    public BytecodeTest() {
        this.reader = new BytecodeReader();
    }

    public void startup() {
        byte[] bytes = new byte[MAX_COUNT];
        reader.read(bytes);
        System.out.println(new String(bytes));
        reader.read(bytes, 1, 4);
        System.out.println(new String(bytes));
    }

    public static void main(String[] args) {
        BytecodeTest bytecodeTest = new BytecodeTest();
        bytecodeTest.startup();
        System.out.println("Bytecode finished.");
    }
}

class BytecodeReader implements Reader {
    @Override
    public void read(byte[] bytes) {
        if (bytes == null) {
            return;
        }

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (i + 1);
        }
    }

    @Override
    public void read(byte[] bytes, int start, int length) {
        if (bytes == null) {
            return;
        }

        if (bytes.length - 1 < start) {
            throw new RuntimeException("start position must be less than bytes.length.");
        }

        for (int i = 0; i < length; i++) {
            if (i + start >= bytes.length) {
                break;
            }

            bytes[i + start] = (byte) (i + start);
        }
    }

}

interface Reader {
    void read(byte[] bytes);
    void read(byte[] bytes, int start, int length);
}
