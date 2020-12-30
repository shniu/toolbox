package io.github.shniu.toolbox.unsafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2020/12/30 16
 */
class ReflectUnsafeTest {
    private Unsafe unsafe;

    @BeforeEach
    public void setUp() {
        unsafe = ReflectUnsafe.getUnsafe();
    }

    @Test
    void getUnsafe() {
        Demo demo = new Demo();

        try {
            Field countField = Demo.class.getDeclaredField("count");
            long countOffset = unsafe.objectFieldOffset(countField);

            int count = unsafe.getInt(demo, countOffset);
            assertEquals(15, count);

            Field staticCountField = Demo.class.getDeclaredField("staticCount");
            long staticFieldOffset = unsafe.staticFieldOffset(staticCountField);
            long staticCount = unsafe.getLong(demo, staticFieldOffset);
            System.out.println(demo.getStaticCount());
            // assertEquals(100, staticCount);

            Field arrField = Demo.class.getDeclaredField("arr");
            long arrOffset = unsafe.objectFieldOffset(arrField);
            int[] arr = (int[]) unsafe.getObject(demo, arrOffset);
            assertNotNull(arr);
            assertEquals(1, arr[0]);
            assertEquals(4, arr[3]);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}