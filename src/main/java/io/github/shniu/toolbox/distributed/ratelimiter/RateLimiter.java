package io.github.shniu.toolbox.distributed.ratelimiter;

/**
 * @author niushaohan
 * @date 2020/11/4 10
 */
public interface RateLimiter {
    default boolean tryAcquire() {
        return tryAcquire(1);
    }

    boolean tryAcquire(int tokens);

    boolean tryRelease();
}
