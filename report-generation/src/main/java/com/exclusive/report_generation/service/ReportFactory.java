package com.exclusive.report_generation.service;

import com.exclusive.report_generation.model.Report;
import com.exclusive.report_generation.utils.enums.ReportType;

public class ReportFactory {

    public static Report generateReport(ReportType reportType) {
        if (reportType == ReportType.DEFAULT) {

            return DefaultReportProvider.getInstance().getDefaultReport();
        }
        // Future implementation when database is available
        return null;
    }
}
