package io.github.shniu.toolbox.distributed.id;

import java.util.UUID;

/**
 * @author niushaohan
 * @date 2020/11/4 10
 */
public class UUIDIdGenerator implements IdGenerator {
    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }
}
