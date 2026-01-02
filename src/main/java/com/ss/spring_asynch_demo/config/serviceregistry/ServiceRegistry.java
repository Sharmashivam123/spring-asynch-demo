/*
package com.ss.spring_asynch_demo.config.serviceregistry;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class ServiceRegistry implements Watcher {
    private static final String REGISTRY_NODE = "/service_registry";
    private final ZooKeeper zooKeeper;
    private String currentZnodeName;

    private List<String> allServiceAddresses;

    ServiceRegistry(@Autowired ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setRegistryNode() {
        createServiceRegistryNode();
    }

    public void registerForUpdates() {
        updateServiceAddresses();
    }

    public void unRegisterFromUpdates() {
        try {
            if (zooKeeper.exists(currentZnodeName, false) != null) {
                zooKeeper.delete(currentZnodeName, -1);
            }
        } catch (InterruptedException | KeeperException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized List<String> getAllServiceAddresses() {
        if (allServiceAddresses == null) {
            updateServiceAddresses();
        }
        return allServiceAddresses;
    }

    public void registerToCluster(String serviceAddress) {
        String serviceNode = REGISTRY_NODE + "/service_";
        try {
            currentZnodeName = zooKeeper.create(serviceNode, serviceAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.info("Registered service at node: " + currentZnodeName + " with address: " + serviceAddress);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createServiceRegistryNode() {
        try {
            if (zooKeeper.exists(REGISTRY_NODE, false) == null) {
                try {
                    zooKeeper.create(REGISTRY_NODE, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void updateServiceAddresses() {
        try {
            List<String> serviceNodes = zooKeeper.getChildren(REGISTRY_NODE, this);
            List<String> addresses = new java.util.ArrayList<>();
            for (String node : serviceNodes) {
                String fullPath = REGISTRY_NODE + "/" + node;
                Stat stat = zooKeeper.exists(fullPath, false);
                if (stat == null) {
                    continue;
                }
                byte[] addressBytes = zooKeeper.getData(fullPath, false, stat);
                addresses.add(new String(addressBytes));
            }
            this.allServiceAddresses = Collections.unmodifiableList(addresses);
            log.info("Updated service addresses: " + this.allServiceAddresses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        updateServiceAddresses();
    }
}
*/
