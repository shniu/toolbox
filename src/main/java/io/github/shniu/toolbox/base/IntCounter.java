package io.github.shniu.toolbox.base;

/**
 * Non-thread-safe int counters.
 *
 * @author niushaohan
 * @date 2020/11/3 19
 */
public class IntCounter {
    private int value;

    public int incrementAndGet() {
        return ++value;
    }

    public int incrementAndGet(int increment) {
        value += increment;
        return value;
    }

    public int get() {
        return value;
    }

    public int getAndIncrement() {
        return value++;
    }

    public int getAndIncrement(int increment) {
        int oldValue = value;
        value += increment;
        return oldValue;
    }
}
