package com.exclusive.report_generation.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LockServiceImpl implements LockService{
    private final RedissonClient redissonClient;

    private static final long WAIT_TIME_SEC = 5;
    private static final long LEASE_TIME_SEC = 30;

    public LockServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean acquireLock(String lockKey, long waitTimeSec, long leaseTimeSec) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTimeSec, leaseTimeSec, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

            return false;
        }
    }

    @Override
    public void releaseLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }
}
