package io.github.shniu.toolbox.api;

/**
 * @author niushaohan
 * @date 2020/12/29 07
 */
public interface MaximumLimitCounter {
    boolean checkMaximumLimit();
    boolean incrementAndCheckMaximumLimit();
    boolean incrementAndCheckMaximumLimit(long v);

    long getMaximumLimit();
}
