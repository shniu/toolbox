package io.github.shniu.toolbox.distributed.ratelimiter.limiter;

import io.github.shniu.toolbox.distributed.ratelimiter.RateLimiter;

/**
 * @author niushaohan
 * @date 2020/11/4 10
 */
public class RedisRateLimiter implements RateLimiter {
    @Override
    public boolean tryAcquire(int tokens) {
        return false;
    }

    @Override
    public boolean tryRelease() {
        return false;
    }
}
