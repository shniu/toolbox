package io.github.shniu.toolbox.concurrent;

import io.github.shniu.toolbox.api.Counter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2021/1/15 10
 */
class ConcurrentCounterTest {

    @Test
    void test_concurrentCounter_within_singleThread() {
        Counter<Long> counter = new ConcurrentCounter();

        counter.increment();
        assertEquals(1, counter.get());

        counter.increment(4L);
        assertEquals(5, counter.get());

        assertEquals(6, counter.incrementAndGet());
        assertEquals(6, counter.get());

        assertEquals(10, counter.incrementAndGet(4L));
        assertEquals(10, counter.get());

        assertEquals(10, counter.getAndIncrement());
        assertEquals(11, counter.get());

        assertEquals(11, counter.getAndIncrement(5L));
        assertEquals(16, counter.get());
    }

    @Test
    void test_concurrentCounter_with_multiThreads() {
        Counter<Long> counter = new ConcurrentCounter();
        Random random = new Random();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<?>> rs = new ArrayList<>();

        // 10 个线程，每个线程增加 1000
        for (int i = 0; i < 10; i++) {
            Future<?> future = executorService.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment(1L);

                    try {
                        Thread.sleep(random.nextInt(15));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            rs.add(future);
        }

        rs.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        Long finalResult = counter.get();
        assertEquals(10000, finalResult);
        System.out.println(finalResult);
    }
}