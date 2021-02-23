package io.github.shniu.toolbox.collections;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author niushaohan
 * @date 2021/2/22 17
 */
public class SoakTest {
    private static final int TIME_UPDATE = 1;
    private static final int SIZE_UPDATE = 2;

    private CoalescingBuffer<Integer, String> buffer;

    public SoakTest(final CoalescingBuffer<Integer, String> buffer) {
        this.buffer = buffer;
    }

    public void run() throws InterruptedException {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        consumer.start();

        consumer.join();
    }

    public static void main(String[] args) throws InterruptedException {
        CoalescingBuffer<Integer, String> buffer = new CoalescingRingBuffer<>(8);

        SoakTest soakTest = new SoakTest(buffer);
        soakTest.run();
    }

    private class Producer extends Thread {
        private Producer() {
            super("producer");
        }

        @Override
        public void run() {
            long messagesSent = 0;
            long lastCountTime = System.currentTimeMillis();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (true) {
                put(TIME_UPDATE, format.format(new Date()));
                put(SIZE_UPDATE, "buffer size = " + Integer.toString(buffer.size()));
                messagesSent += 2;

                long now = System.currentTimeMillis();
                if (now > lastCountTime + 10000) {
                    lastCountTime = now;
                    put("sent " + ++messagesSent + " messages");
                }
            }
        }

        private void put(String message) {
            boolean success = buffer.offer(message);
            if (!success) {
                throw new AssertionError("offer of " + message + " failed");
            }
        }

        private void put(Integer key, String value) {
            boolean success = buffer.offer(key, value);
            if (!success) {
                throw new AssertionError("offer of " + key + " = " + value + " + failed");
            }
        }
    }

    private class Consumer extends Thread {
        private Consumer() {
            super("consumer");
        }

        @Override
        public void run() {
            ArrayList<String> messages = new ArrayList<String>();

            while (true) {
                buffer.poll(messages, 10);
                for (String message : messages) {
                    System.out.println(message);
                }
                messages.clear();
                System.out.println("-----------------");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new AssertionError(e);
                }
            }
        }
    }
}
