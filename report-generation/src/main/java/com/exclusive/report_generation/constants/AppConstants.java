package com.exclusive.report_generation.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConstants {

    public static String REPORT_STORAGE_BASE_URL;
    public static long REPORT_MIN_SIZE_KB;
    public static long REPORT_RANDOM_SIZE_RANGE_KB;
    public static String DEFAULT_REPORT_NAME;
    public static String DEFAULT_GENERATED_BY;

    public static String REPORT_LOCK_KEY;
    public static long REPORT_LOCK_WAIT_TIME_SEC;
    public static long REPORT_LOCK_LEASE_TIME_SEC;

    @Value("${app.report.storage.base-url}")
    public void setReportStorageBaseUrl(String url) {
        AppConstants.REPORT_STORAGE_BASE_URL = url;
    }

    @Value("${app.report.size.min}")
    public void setReportMinSizeKb(long size) {
        AppConstants.REPORT_MIN_SIZE_KB = size;
    }

    @Value("${app.report.size.range}")
    public void setReportRandomSizeRangeKb(long range) {
        AppConstants.REPORT_RANDOM_SIZE_RANGE_KB = range;
    }

    @Value("${app.report.default.name}")
    public void setDefaultReportName(String name) {
        AppConstants.DEFAULT_REPORT_NAME = name;
    }

    @Value("${app.report.default.generated-by}")
    public void setDefaultGeneratedBy(String generatedBy) {
        AppConstants.DEFAULT_GENERATED_BY = generatedBy;
    }

    @Value("${app.report.lock.key}")
    public void setReportLockKey(String lockKey) {
        AppConstants.REPORT_LOCK_KEY = lockKey;
    }
    @Value("${app.report.lock.wait-time-sec}")
    public void setReportLockWaitTimeSec(long waitTime) {
        AppConstants.REPORT_LOCK_WAIT_TIME_SEC = waitTime;
    }

    @Value("${app.report.lock.lease-time-sec}")
    public void setReportLockLeaseTimeSec(long leaseTime) {
        AppConstants.REPORT_LOCK_LEASE_TIME_SEC = leaseTime;
    }
}
