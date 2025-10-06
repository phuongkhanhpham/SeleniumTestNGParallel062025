package com.anhtester.handleTable;

import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class HandleTable extends BaseTest {
    @Test
    public void testSearchDataInTable() {
        getLoginPage().loginCRM("admin@example.com", "123456");
        getDashboardPage().clickMenuCustomers();
        getCustomerPage().searchAndCheckDataInTable_Contains(3, "CustomerName", "Company");
    }
}
