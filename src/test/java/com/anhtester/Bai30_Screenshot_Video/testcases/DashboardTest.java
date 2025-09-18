package com.anhtester.Bai30_Screenshot_Video.testcases;

import com.anhtester.Bai30_Screenshot_Video.pages.DashboardPage;
import com.anhtester.Bai30_Screenshot_Video.pages.LoginPage;
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
