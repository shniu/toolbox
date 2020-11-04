package io.github.shniu.toolbox.base;

/**
 * Non-thread-safe long counters.
 *
 * @author niushaohan
 * @date 2020/11/4 09
 */
public class LongCounter {
    private long value;

    public long incrementAndGet() {
        return ++value;
    }

    public long incrementAndGet(long increment) {
        value += increment;
        return value;
    }

    public long get() {
        return value;
    }

    public long getAndIncrement() {
        return value++;
    }

    public long getAndIncrement(long increment) {
        long oldValue = value;
        value += increment;
        return oldValue;
    }
}
