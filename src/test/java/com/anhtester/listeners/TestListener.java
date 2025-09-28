package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.reports.ExtentReportManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static int test_total;
    private static int test_passed_total;
    private static int test_failed_total;
    private static int test_skipped_total;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("\uD83D\uDEE0Setup môi trường onStart: " + result.getStartDate());

//        CaptureHelper.startRecord("VideoSuite01");
        // Load file Properties config
        PropertiesHelper.loadAllFiles();

        // Có thể kết nối Database trước để lấy data test
        // Call API bên thứ 3 để xác thực 1 cái gì đó khi cần
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("⭕Kết thúc bộ test: " + result.getEndDate());
        LogUtils.info("Test total: " + test_total);
        LogUtils.info("Test passed total: " + test_passed_total);
        LogUtils.info("Test failed total: " + test_failed_total);
        LogUtils.info("Test skipped total: " + test_skipped_total);

//        CaptureHelper.stopRecord();
        // Gửi mail (đính kèm file logs, file report)
        // Xuất report
        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Ghi vào logs File
        // Ghi vòa report chi tiết từng bước
        LogUtils.info("▶Bắt đầu chạy test case: " + result.getName());
        test_total++;
        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));

        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("✅Test case " + result.getName() + " is passed.");
        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, "✅Test case " + result.getName() + " is passed.");

        test_passed_total++;

        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌Test case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable());

        //Extent Report
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.addScreenshot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, "❌Test case " + result.getName() + " is failed.");

        test_failed_total++;
        CaptureHelper.captureScreenshot(result.getName());

        CaptureHelper.stopRecord();
        // Tạo ticket Jira
        // Gửi hình chụp và logs lên Slack/ Telegram/ MSTeam
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("\uD83D\uDFE1Test case " + result.getName() + " is skipped.");
        LogUtils.warn(result.getThrowable());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.SKIP, "\uD83D\uDFE1Test case " + result.getName() + " is skipped.");

        test_skipped_total++;

        CaptureHelper.stopRecord();
        // Tạo ticket Jira
        // Gửi hình chụp và logs lên Slack/ Telegram/ MSTeam
    }
}
