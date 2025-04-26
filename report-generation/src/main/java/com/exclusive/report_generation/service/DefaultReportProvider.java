package com.exclusive.report_generation.service;

import com.exclusive.report_generation.constants.AppConstants;
import com.exclusive.report_generation.model.Report;
import com.exclusive.report_generation.utils.enums.ReportType;

public class DefaultReportProvider {

    private static DefaultReportProvider instance;

    private final Report defaultReport;

    private DefaultReportProvider() {

        this.defaultReport = new Report(
                AppConstants.DEFAULT_REPORT_NAME,
                AppConstants.DEFAULT_GENERATED_BY,
                ReportType.DAILY
        );
    }

    public static synchronized DefaultReportProvider getInstance() {
        if (instance == null) {
            instance = new DefaultReportProvider();
        }

        return instance;
    }

    public Report getDefaultReport() {

        return defaultReport;
    }
}
