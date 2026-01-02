/*
package com.ss.spring_asynch_demo.config.leaderelection;

import com.ss.spring_asynch_demo.config.serviceregistry.OnElectionCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class LeaderElection implements Watcher {
    private final ZooKeeper zk;
    private String currentZnodeName;
    private static final String TARGET_ZNODE = "/target_znode";
    private static final String ELECTION_NAMESPACE = "/leader_election";
    private final OnElectionCallback onElectionCallback;

    @Autowired
    public LeaderElection(ZooKeeper zk, OnElectionCallback onElectionCallback) {
        this.zk = zk;
        this.onElectionCallback = onElectionCallback;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startLeaderElection() throws InterruptedException, KeeperException {
        try {
            volunteerForLeaderShip();
            electLeader();
//            watchTargetZnode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void volunteerForLeaderShip() throws InterruptedException, KeeperException {
        // Implementation for volunteering for leadership
        String znodePrefix = ELECTION_NAMESPACE + "/candidate_";
        String znodePath = zk.create(znodePrefix, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        log.info("Znode name: " + znodePath);
        this.currentZnodeName = znodePath.replace(ELECTION_NAMESPACE + "/", "");
    }

    public void electLeader() throws KeeperException, InterruptedException, UnknownHostException {
        // Implementation for leader election
        log.info("Electing leader...");
        Stat predecessorStat = null;
        String predecessorZnodeName = null;

        while (predecessorStat == null) {
            List<String> children = zk.getChildren(ELECTION_NAMESPACE, false);
            Collections.sort(children);

            String smallestChild = children.get(0);
            if (smallestChild.equals(currentZnodeName)) {
                log.info("I am the leader");
                onElectionCallback.onElectedToBeLeader();
                break;
            } else {
                log.info("I am not the leader. The leader is: " + smallestChild);
                int predecessor = Collections.binarySearch(children, currentZnodeName) - 1;
                predecessorZnodeName = children.get(predecessor);
                predecessorStat = zk.exists(ELECTION_NAMESPACE + "/" + predecessorZnodeName, this);
                onElectionCallback.onRevokedLeadership();
            }
        }
    }

    */
/*public void watchTargetZnode() throws InterruptedException, KeeperException {
        Stat stat = zk.exists(TARGET_ZNODE, this);
        if (stat == null) {
            log.info("Target znode does not exist");
        } else {
            byte[] data = zk.getData(TARGET_ZNODE, this, stat);
            String dataStr = new String(data);
            log.info("Data of target znode: " + dataStr);

            List<String> children = zk.getChildren(TARGET_ZNODE, this);
            log.info("Children of target znode: " + children);
        }
    }*//*


    @Override
    public void process(WatchedEvent event) {
        // Handle events here
        switch (event.getType()) {
            case None:
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    log.info("Successfully connected to Zookeeper");
                } else {
                    synchronized (zk) {
                        log.info("Disconnected from Zookeeper event");
                        zk.notifyAll();
                    }
                }
                break;
            case NodeDeleted:
                try {
                    electLeader();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (KeeperException e) {
                    throw new RuntimeException(e);
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
                log.info("Node deleted event received for path: " + event.getPath());
                break;
            case NodeCreated:
                log.info("Node created event received for path: " + event.getPath());
                break;
            case NodeDataChanged:
                log.info("Node data changed event received for path: " + event.getPath());
                break;
            case NodeChildrenChanged:
                log.info("Node children changed event received for path: " + event.getPath());
                break;
        }
    }
}
*/
