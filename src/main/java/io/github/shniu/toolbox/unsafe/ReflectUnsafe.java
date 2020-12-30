package io.github.shniu.toolbox.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author niushaohan
 * @date 2020/12/29 06
 */
public final class ReflectUnsafe {
    static Unsafe UNSAFE;

    static {
        try {
            // Get an instance of Unsafe by reflection,
            // bypassing the limitations of the bootstrap class loader.
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // swallow exception
        }
    }

    public static Unsafe getUnsafe() {
        if (Objects.isNull(UNSAFE)) {
            throw new RuntimeException("UNSAFE is null.");
        }
        return UNSAFE;
    }
}
