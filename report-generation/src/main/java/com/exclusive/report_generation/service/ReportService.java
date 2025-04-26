package com.exclusive.report_generation.service;

import com.exclusive.report_generation.constants.AppConstants;
import com.exclusive.report_generation.model.Report;
import com.exclusive.report_generation.utils.enums.ReportType;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportService.class);
    private final LockService lockService;

    public ReportService( LockService lockService) {
        this.lockService = lockService;
    }

    public Report generateReport(ReportType reportType) {

        boolean lockAcquired = false;
        try {
            String lockKey = AppConstants.REPORT_LOCK_KEY;
            long waitTimeSec = AppConstants.REPORT_LOCK_WAIT_TIME_SEC;
            long leaseTimeSec = AppConstants.REPORT_LOCK_LEASE_TIME_SEC;

            lockAcquired = lockService.acquireLock(lockKey, waitTimeSec, leaseTimeSec);

            if (lockAcquired) {
                if (reportType == null) {
                    reportType = ReportType.DEFAULT;
                }

                log.info("Lock acquired via LockServiceImpl. Generating report of type: {}", reportType);
                Thread.sleep(30000);

                return ReportFactory.generateReport(reportType);

            } else {
                log.warn("Could not acquire lock. Another report generation is in progress.");

                throw new RuntimeException(
                        "Another report generation is already in progress. " +
                                "Please try again later."
                );
            }

        } catch (InterruptedException e) {

            throw new RuntimeException(e.getMessage());
        } finally {
            if (lockAcquired) {
                lockService.releaseLock(AppConstants.REPORT_LOCK_KEY);
                log.info("Lock released after report generation.");
            }
        }
    }
}
