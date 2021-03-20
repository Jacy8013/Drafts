package io.jacy.drafts.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author Jacy
 */
public class ZkApi {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("172.100.7.130:2181,172.100.7.131:2181,172.100.7.132:2181,172.100.7.133:2181/drafts",
                30000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                switch (watchedEvent.getState()) {
                    case Unknown:
                        break;
                    case Disconnected:
                        System.out.println("Disconnected...");
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        System.out.println("SyncConnected...");
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                    case Closed:
                        break;
                }

                switch (watchedEvent.getType()) {
                    case None:
                        System.out.println("None...");
                        break;
                    case NodeCreated:
                        System.out.println("NodeCreated...");
                        break;
                    case NodeDeleted:
                        System.out.println("NodeDeleted...");
                        break;
                    case NodeDataChanged:
                        break;
                    case NodeChildrenChanged:
                        break;
                    case DataWatchRemoved:
                        break;
                    case ChildWatchRemoved:
                        break;
                    case PersistentWatchRemoved:
                        break;
                }
                System.out.println(watchedEvent.getPath());
                System.out.println(watchedEvent.getState());
                System.out.println(watchedEvent.getType());
                System.out.println(watchedEvent.getWrapper().getPath());
            }
        });
        System.out.println(zk.toString());
        System.out.println("===============");

        Stat stat = new Stat();
        byte[] data = zk.getData("/a", false, stat);
        System.out.println(new String(data));

//        System.out.println("====================");
//        // 临时节点, 不能有child
//        String createD = zk.create("/d", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//        System.out.println(createD);

        System.out.println("====================");
        //
        zk.create("/e", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, String name) {
                System.out.println(rc);
                System.out.println(path);
                System.out.println(ctx);
                System.out.println(name);
            }
        }, "e");

        System.in.read();
    }
}
