package com.anhtester.Bai31_TestListener.testcases;

import com.anhtester.Bai31_TestListener.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.common.LocatorCRM;
import com.anhtester.dataproviders.DataProviderFactory;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.keywords.WebUI;
import com.anhtester.listeners.TestListener;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

//@Listeners(TestListener.class) // Khai báo ở class cha BaseTest, mình extends xài ké
public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
    public void loginSuccess(String email, String pasword) {
        loginPage = new LoginPage();
        loginPage.loginCRM(email, pasword);
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 1, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProvider(String email, String pasword) {
        loginPage = new LoginPage();
        loginPage.loginCRM(email, pasword);
        WebUI.waitForElementVisible(By.xpath(LocatorCRM.menuDashboard));
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 1, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessFromDataProviderHashtable(Hashtable <String, String> data) {
        loginPage = new LoginPage();
        loginPage.loginCRM(data.get("Email"), data.get("Password"));
//        WebUI.waitForElementVisible(By.xpath(LocatorCRM.menuDashboard));
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 2)
    public void loginFailWithEmailInvalid() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFail();
    }

    @Test(priority = 3)
    public void loginFailWithPasswordInvalid() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "1234567");
        loginPage.verifyLoginFail(); // Invalid email or password

    }

    @Test(priority = 4)
    public void loginFailWithEmailNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("", "1234567");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test(priority = 5)
    public void loginFailWithPasswordNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");

    }

    @Test(priority = 6)
    public void loginFailWithEmailNullAndPasswordNull() {
        loginPage = new LoginPage();
        loginPage.loginCRM("", "");
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }
}
