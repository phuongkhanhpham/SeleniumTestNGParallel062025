package com.anhtester.Bai31_TestListener.testcases;

import com.anhtester.Bai31_TestListener.pages.DashboardPage;
import com.anhtester.Bai31_TestListener.pages.LoginPage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    DashboardPage dashboardPage;
    LoginPage loginPage;

    @Test
    public void testCheckDashboardTotal() throws InterruptedException {
        loginPage = new LoginPage();
//        loginPage.loginCRM(); // Chỉ Login
//
//        dashboardPage = new DashboardPage(driver);

        dashboardPage = loginPage.loginCRM();
        dashboardPage.verifyInvoicesAwaitingPayment("1 / 3");
    }
}
