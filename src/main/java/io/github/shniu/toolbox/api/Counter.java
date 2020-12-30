package io.github.shniu.toolbox.api;

/**
 * @author niushaohan
 * @date 2020/12/29 07
 */
public interface Counter<T> {
    void increment();
    void increment(T v);

    T incrementAndGet();
    T incrementAndGet(T v);

    T get();

    T getAndIncrement();
    T getAndIncrement(T v);
}
