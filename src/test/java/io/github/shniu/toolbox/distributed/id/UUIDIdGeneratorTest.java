package io.github.shniu.toolbox.distributed.id;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author niushaohan
 * @date 2020/11/4 10
 */
class UUIDIdGeneratorTest {

    @Test
    void test_uuidGenerator() {
        IdGenerator idGenerator = new UUIDIdGenerator();
        String id = idGenerator.nextId();

        assertNotNull(id);

        UUID uuid = UUID.fromString(id);
        assertNotNull(uuid);

        System.out.println(id);
        assertEquals(id, uuid.toString());
    }
}