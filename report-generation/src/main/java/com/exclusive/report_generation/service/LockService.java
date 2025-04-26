package com.exclusive.report_generation.service;

public interface LockService {

    boolean acquireLock(String lockKey, long waitTimeSec, long leaseTimeSec);
    void releaseLock(String lockKey);
}
