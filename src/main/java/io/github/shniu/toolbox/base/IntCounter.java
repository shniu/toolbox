package io.github.shniu.toolbox.base;

import io.github.shniu.toolbox.api.Counter;

/**
 * Non-thread-safe int counters.
 *
 * @author niushaohan
 * @date 2020/11/3 19
 */
public class IntCounter implements Counter<Integer> {
    private Integer value;

    public IntCounter() {
        this(0);
    }

    public IntCounter(Integer value) {
        this.value = value;
    }

    @Override
    public void increment() {
        value++;
    }

    @Override
    public void increment(Integer v) {
        value += v;
    }

    public Integer incrementAndGet() {
        return ++value;
    }

    public Integer incrementAndGet(Integer v) {
        value += v;
        return value;
    }

    public Integer get() {
        return value;
    }

    public Integer getAndIncrement() {
        return value++;
    }

    @Override
    public Integer getAndIncrement(Integer v) {
        Integer o = value;
        value = o + v;
        return o;
    }

    public int getAndIncrement(int increment) {
        int oldValue = value;
        value += increment;
        return oldValue;
    }
}
