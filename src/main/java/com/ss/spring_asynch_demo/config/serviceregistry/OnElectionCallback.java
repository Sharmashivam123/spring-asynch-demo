package com.ss.spring_asynch_demo.config.serviceregistry;

public interface OnElectionCallback {
    default void onElectedToBeLeader() {
    }

    default void onRevokedLeadership() {
    }
}
