package io.github.shniu.toolbox.collections;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author niushaohan
 * @date 2021/2/23 15
 */
public class AtomicReferenceArrayTest {

    @Test
    void test_atomicReferenceArray() {

        AtomicReferenceArray<String> atomicReferenceArray = new AtomicReferenceArray<>(10);

        atomicReferenceArray.set(0, "hello");
        atomicReferenceArray.lazySet(1, "world");

        System.out.println(atomicReferenceArray.length());
    }
}
