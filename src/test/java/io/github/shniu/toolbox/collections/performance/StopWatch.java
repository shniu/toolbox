package io.github.shniu.toolbox.collections.performance;

import java.util.concurrent.CountDownLatch;

/**
 * @author niushaohan
 * @date 2021/2/23 15
 */
public final class StopWatch {
    private final CountDownLatch startingGate = new CountDownLatch(2);
    private volatile long startTime;
    private volatile long endTime;

    void consumerIsReady() {
        awaitStart();
    }

    private void awaitStart() {
        startingGate.countDown();

        try {
            startingGate.await();
        }
        catch (InterruptedException exception) {
            throw new AssertionError(exception);
        }
    }

    void producerIsReady() {
        awaitStart();
        startTime = System.nanoTime();
    }

    void consumerIsDone() {
        endTime = System.nanoTime();
    }

    long nanosTaken() {
        return endTime - startTime;
    }
}
