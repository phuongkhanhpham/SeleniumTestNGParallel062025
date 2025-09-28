package com.anhtester.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    // Setup thông tin, hình dạng của report: muốn report name gì, sáng tối màu mè ra sao
    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("exports/reports/extentreport/extentreport.html");
        reporter.config().setReportName("Extent Report | Anh Tester");
//        reporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Anh Tester");
        extentReports.setSystemInfo("Author", "Anh Tester");
        return extentReports;
    }

}

