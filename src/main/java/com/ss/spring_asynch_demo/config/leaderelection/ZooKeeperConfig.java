package com.ss.spring_asynch_demo.config.leaderelection;

import jakarta.annotation.PreDestroy;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZooKeeperConfig implements Watcher {

    public static final String ZOOPKEEPER_ADDRESS = "localhost:2181";
    public static int SESSION_TIMEOUT = 300000;
    private ZooKeeper zk;

    @Bean
    public ZooKeeper connectToZookeeper() throws Exception {
        this.zk = new ZooKeeper(ZOOPKEEPER_ADDRESS, SESSION_TIMEOUT, this);
        return this.zk;
    }

    @PreDestroy
    public void closeZookeeperConnection() throws InterruptedException {
        zk.close();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.EventType eventType = watchedEvent.getType();
        switch (eventType) {
            case None:
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Successfully connected to ZooKeeper");
                } else {
                    System.out.println("Disconnected from ZooKeeper");
                }
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
        }
    }
}
