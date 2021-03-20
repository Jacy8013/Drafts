package io.jacy.drafts.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 通过异步回调的 lock
 *
 * @author Jacy
 */
public class AsyncLock implements Lock {

    private final static String PATH = "/async";
    private ZooKeeper zooKeeper;
    private static final CountDownLatch LATCH = new CountDownLatch(1);
    private MyWatch watch;

    public AsyncLock(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    @Override
    public void tryLock() throws InterruptedException {
        watch = new MyWatch(zooKeeper, PATH, LATCH);
        zooKeeper.create(PATH + "/l", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, watch, "create");

        System.out.println("============= await~ ");
        LATCH.await();
    }

    @Override
    public void release() {
        try {
            LATCH.await();
            if (watch.getLockName() != null) {
                zooKeeper.delete(PATH + "/" + watch.getLockName(), -1);
            }
            zooKeeper.close();
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}

class MyWatch implements Watcher, AsyncCallback.StringCallback, AsyncCallback.ChildrenCallback, AsyncCallback.StatCallback {
    private String parentPath;
    private ZooKeeper zk;

    public String getLockName() {
        return lockName;
    }

    private String lockName;
    private CountDownLatch latch;

    public MyWatch(ZooKeeper zk, String path, CountDownLatch latch) {
        this.zk = zk;
        this.parentPath = path;
        this.latch = latch;
    }

    /**
     * 注册 子节点变化 和 前一节点被删除的回调事件
     *
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("4------------ NodeDeleted, " + zk.getSessionId() + ", " + Thread.currentThread().getName() + ", " + event.getPath() + ", " + event.getType());
            zk.getChildren(parentPath, false, this, "watcher process....");
        }
    }

    /**
     * 子节点发生变化后的callback
     *
     * @param rc
     * @param path
     * @param ctx
     * @param children
     */
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children) {
        Collections.sort(children);
        int currentIndex;
        System.out.println("2.1------- " + lockName + ", " + children);
        if ((currentIndex = children.indexOf(lockName)) == 0) {
            System.out.println("2------------ locked, " + path + "/" + lockName + ", " + zk.getSessionId() + ", " + Thread.currentThread().getName());
            try {
                zk.setData(path, lockName.getBytes(), -1);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        } else {
            String lastPath = path + "/" + children.get(currentIndex - 1);
            zk.exists(lastPath, this, this, "exists");
            System.out.println("3------------ watch, " + lastPath + ", " + zk.getSessionId() + ", " + Thread.currentThread().getName());
        }
    }

    /**
     * 创建临时序列节点后的callback
     *
     * @param rc
     * @param path
     * @param ctx
     * @param name
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (name != null) {
            System.out.println("0------------ created dir, " + zk.getSessionId() + ", " + Thread.currentThread().getName() + ", " + name);
            this.lockName = name.substring(name.lastIndexOf("/") + 1);
            zk.getChildren(parentPath, false, this, "created...");
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            System.out.println("5--------------- " + path + ", " + Thread.currentThread().getName() + ", " + ctx + ", " + stat);
        }
    }
}