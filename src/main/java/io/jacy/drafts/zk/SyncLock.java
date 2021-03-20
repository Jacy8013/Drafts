package io.jacy.drafts.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 同步方式 lock
 *
 * @author Jacy
 */
public class SyncLock implements Lock{
    private static final Random RANDOM = new Random();
    private static final String LOCK_PATH = "/sync";

    private ZooKeeper zooKeeper;
    private String lockDir;

    public SyncLock(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public String getLockDir() {
        return lockDir;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    @Override
    public void tryLock() {
        int i = 0;
        try {
            lockDir = zooKeeper.create(LOCK_PATH + "/l", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            List<String> children;
            String lockedPath = lockDir.substring(lockDir.lastIndexOf("/") + 1);
            while ((children = zooKeeper.getChildren(LOCK_PATH, false)) != null) {
                Collections.sort(children);
                if (children.indexOf(lockedPath) == 0) {
                    return;
                }
                i++;
                TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(2) + 1);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " locked... " + i + " --- " + lockDir + ", session: " + Long.toHexString(zooKeeper.getSessionId()));
        }
    }

    @Override
    public void release() {
        try {
            System.out.println(Thread.currentThread().getName() + " release... " + lockDir + ", session: " + Long.toHexString(zooKeeper.getSessionId()));
            zooKeeper.delete(lockDir, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        } finally {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
