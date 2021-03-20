package io.jacy.drafts.zk;

/**
 * @author Jacy
 */
public interface Lock {
    /**
     * 获取锁
     */
    void tryLock() throws InterruptedException;

    /**
     * 释放
     */
    void release();
}
