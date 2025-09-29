package com.anhtester.Bai34_AllureReport.testcases;

import com.anhtester.Bai34_AllureReport.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.common.LocatorCRM;
import com.anhtester.dataproviders.DataProviderFactory;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Hashtable;

//@Listeners(TestListener.class) // Khai báo ở class cha BaseTest, mình extends xài ké
public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Epic("Regression")
    @Feature("DMS")
    @Story("Login")
    @Owner("Khanh Phạm")
    @Severity(SeverityLevel.NORMAL)
    @Link("Link document")
    @Issue("Ticket bug Jira")
    @Test(priority = 1, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String pasword) {
        loginPage = new LoginPage();
        loginPage.loginCRM(email, pasword);
        loginPage.verifyLoginSuccess();
    }

    @Epic("Regression")
    @Feature("DMS")
    @Story("Login")
    @Owner("Khanh Phạm")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProvider(String email, String pasword) {
        loginPage = new LoginPage();
        loginPage.loginCRM(email, pasword);
        WebUI.waitForElementVisible(By.xpath(LocatorCRM.menuDashboard));
        loginPage.verifyLoginSuccess();
    }

    @Epic("Regression")
    @Feature("Inventory")
    @Story("Login")
    @Owner("Phụng Nguyễn")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProviderHashtable(Hashtable <String, String> data) {
        loginPage = new LoginPage();
        loginPage.loginCRM(data.get("Email"), data.get("Password"));
//        WebUI.waitForElementVisible(By.xpath(LocatorCRM.menuDashboard));
        loginPage.verifyLoginSuccess();
    }

    @Feature("Smoke")
    @Owner("Hiệp Đỗ")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void loginFailWithEmailInvalid() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFail();
    }

    @Feature("Smoke")
    @Owner("Phụng Nguyễn")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3)
    public void loginFailWithPasswordInvalid() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "1234567");
        loginPage.verifyLoginFail(); // Invalid email or password

    }

    @Feature("Smoke")
    @Owner("Hiệp Đỗ")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 4)
    public void loginFailWithEmailNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("", "1234567");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Feature("Regression")
    @Owner("Thảo Phạm")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 5)
    public void loginFailWithPasswordNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");

    }

    @Feature("Regression")
    @Owner("Thảo Phạm")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 6)
    public void loginFailWithEmailNullAndPasswordNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("", "");
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }
}
