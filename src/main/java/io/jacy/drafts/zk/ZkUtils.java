package io.jacy.drafts.zk;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jacy
 */
public class ZkUtils {
    private static String hostPort = "172.100.7.130:2181,172.100.7.131:2181,172.100.7.132:2181,172.100.7.133:2181";

    public static ZooKeeper get(String path) throws IOException, InterruptedException {
        if (StringUtils.isEmpty(path)) {
            path = "";
        }
        CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(hostPort + path, 3000, event -> {
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                latch.countDown();
            }
        });
        latch.await();
        return zk;
    }

    public static void main(String[] args) {
        String a = null;
        if (StringUtils.isEmpty(a)) {
            a = "";
        }
        System.out.println("11" + a);
    }
}
