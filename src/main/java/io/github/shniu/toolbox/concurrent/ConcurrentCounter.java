package io.github.shniu.toolbox.concurrent;

import io.github.shniu.toolbox.api.Counter;
import io.github.shniu.toolbox.unsafe.ReflectUnsafe;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author niushaohan
 * @date 2020/12/30 16
 */
public class ConcurrentCounter implements Counter<Long> {
    private static Unsafe unsafe;
    private static long valueOffset;

    private volatile Long value = 0L;

    static {
        unsafe = ReflectUnsafe.getUnsafe();

        Field field = null;
        try {
            field = ConcurrentCounter.class.getDeclaredField("value");
            valueOffset = unsafe.objectFieldOffset(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public ConcurrentCounter() {
        this.value = 0L;
    }

    public ConcurrentCounter(final Long value) {
        this.value = value;
    }

    @Override
    public void increment() {
        casIncr(1L);
    }

    @Override
    public void increment(final Long v) {
        casIncr(v);
    }

    @Override
    public Long incrementAndGet() {
        Long casIncr = casIncr(1L);
        return casIncr + 1;
    }

    @Override
    public Long incrementAndGet(final Long v) {
        Long casIncr = casIncr(v);
        return casIncr + v;
    }

    @Override
    public Long get() {
        return this.value;
    }

    @Override
    public Long getAndIncrement() {
        return casIncr(1L);
    }

    @Override
    public Long getAndIncrement(final Long v) {
        return casIncr(v);
    }

    private Long casIncr(final Long v) {
        if (v <= 0) {
            return this.value;
        }

        // System.out.println(Thread.currentThread().getName());

//        System.out.println("v: " + v + ", valueOffset: " + valueOffset
//                + ", value is " + (Long) unsafe.getObject(this, valueOffset));

        Long o = this.value;
        while (!unsafe.compareAndSwapObject(this, valueOffset, o, o + v)) {
            o = this.value;
        }

        return o;
    }
}
