package com.exclusive.report_generation.model;

import com.exclusive.report_generation.constants.AppConstants;
import com.exclusive.report_generation.utils.enums.ReportStatus;
import com.exclusive.report_generation.utils.enums.ReportType;

import java.time.Instant;
import java.util.UUID;

public class Report {

    private String id;
    private String reportName;
    private Instant generatedAt;
    private ReportStatus status;
    private String generatedBy;
    private ReportType reportType;
    private long sizeInKb;
    private String downloadLink;

    public Report(String reportName, String generatedBy, ReportType reportType) {
        this.id = UUID.randomUUID().toString();
        this.reportName = reportName;
        this.generatedAt = Instant.now();
        this.status = ReportStatus.PENDING;
        this.generatedBy = generatedBy;
        this.reportType = reportType;
        this.sizeInKb = generateRandomSize();
        this.downloadLink = generateDummyLink();
    }

    private long generateRandomSize() {

        return AppConstants.REPORT_MIN_SIZE_KB + (long) (Math.random() * AppConstants.REPORT_RANDOM_SIZE_RANGE_KB);
    }

    private String generateDummyLink() {

        return AppConstants.REPORT_STORAGE_BASE_URL + id + ".pdf";
    }


    public void markSuccess() {
        this.status = ReportStatus.SUCCESS;
    }

    public void markFailed() {
        this.status = ReportStatus.FAILED;
    }

    public String getId() {

        return id;
    }

    public String getReportName() {

        return reportName;
    }

    public Instant getGeneratedAt() {

        return generatedAt;
    }

    public ReportStatus getStatus() {

        return status;
    }

    public String getGeneratedBy() {

        return generatedBy;
    }

    public ReportType getReportType() {

        return reportType;
    }

    public long getSizeInKb() {

        return sizeInKb;
    }

    public String getDownloadLink() {

        return downloadLink;
    }

}
