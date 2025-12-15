package com.ss.spring_asynch_demo.config.serviceregistry;

import java.net.UnknownHostException;

public interface OnElectionCallback {
    default void onElectedToBeLeader() {
    }

    default void onRevokedLeadership() throws UnknownHostException {
    }
}
