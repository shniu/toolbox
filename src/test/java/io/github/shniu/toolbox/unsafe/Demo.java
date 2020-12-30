package io.github.shniu.toolbox.unsafe;

/**
 * @author niushaohan
 * @date 2020/12/30 16
 */
public class Demo {
    private int count;
    private static long staticCount = 100;
    private int[] arr = {1, 2, 3, 4};

    public Demo() {
        count = 15;
    }

    public long getStaticCount() {
        return staticCount;
    }
}
