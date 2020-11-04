package io.github.shniu.toolbox.distributed.id;

/**
 * Distributed id generator interface, e.g. snowflake, db-based etc.
 *
 * @author niushaohan
 * @date 2020/11/4 09
 */
public interface IdGenerator {
    String nextId();
}
