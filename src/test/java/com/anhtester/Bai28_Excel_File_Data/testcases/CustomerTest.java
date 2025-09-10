package com.anhtester.Bai28_Excel_File_Data.testcases;

import com.anhtester.Bai28_Excel_File_Data.pages.CustomerPage;
import com.anhtester.Bai28_Excel_File_Data.pages.DashboardPage;
import com.anhtester.Bai28_Excel_File_Data.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;

    @Test
    public void testAddNewCustomer() throws InterruptedException {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/CRM.xlsx", "Customer");


        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomers();

        customerPage.verifyNavigateToCustomerPage();
        int beforeTotal = customerPage.getCustomerTotal();
        customerPage.clickButtonAddNewCusTomer();
        customerPage.submitDataForNewCustomer(1);
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(1);

        customerPage.clickMenuCustomers();
        int afterTotal = customerPage.getCustomerTotal();
        Assert.assertEquals(afterTotal, beforeTotal + 1, "The total customer before and after adding new are not matched.");
        excelHelper.setCellData("Passed", "Status",1);
    }

    @Test
    public void testAddNewCustomer_SearchInTable() throws InterruptedException {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomers();

        customerPage.verifyNavigateToCustomerPage();
        int beforeTotal = customerPage.getCustomerTotal();
        customerPage.clickButtonAddNewCusTomer();
        customerPage.submitDataForNewCustomer(2);

        // Search and check customer name in table
        customerPage.searchAndCheckCustomerInTable(2);
        customerPage.clickFirstCustomer();

        // Verify data of new customer in profile page
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(2);

        // Compare total customer
        customerPage.clickMenuCustomers();
        int afterTotal = customerPage.getCustomerTotal();
        Assert.assertEquals(afterTotal, beforeTotal + 1, "The total customer before and after adding new are not matched.");
    }
}
