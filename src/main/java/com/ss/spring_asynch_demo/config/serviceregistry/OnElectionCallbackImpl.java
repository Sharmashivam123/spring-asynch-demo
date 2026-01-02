/*
package com.ss.spring_asynch_demo.config.serviceregistry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class OnElectionCallbackImpl implements OnElectionCallback {

    private final ServiceRegistry serviceRegistry;

    @Value("${server.port:8085}")
    private String serverPort;

    public OnElectionCallbackImpl(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void onElectedToBeLeader() {
        serviceRegistry.unRegisterFromUpdates();
        serviceRegistry.registerForUpdates();
    }

    @Override
    public void onRevokedLeadership() throws UnknownHostException {
        String serviceAddress = String.format("http://%s:%s",
                InetAddress.getLocalHost().getCanonicalHostName(), serverPort);
        serviceRegistry.registerToCluster(serviceAddress);
    }
}
*/
