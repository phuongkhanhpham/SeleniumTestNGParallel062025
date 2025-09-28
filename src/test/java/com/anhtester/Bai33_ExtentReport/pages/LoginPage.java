package com.anhtester.Bai33_ExtentReport.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    // Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class, 'alert-danger')]");
    private By errorMessage1 = By.xpath("//div[contains(@class, 'alert-danger')][1]");
    private By errorMessage2 = By.xpath("//div[contains(@class, 'alert-danger')][2]");


    // Có thể dùng hàm chung đã viết ở WebUI, tuy nhiên có thể tạo thêm hàm riêng để đặt tên rõ hơn
    private void setEmail(String email) {
//        DriverManager.getDriver().findElement(inputEmail).sendKeys(email);
        WebUI.setText(inputEmail, email);
    }

    private void setPassword(String password) {
        DriverManager.getDriver().findElement(inputPassword).sendKeys(password);
    }

    private void clickLoginButton() {
        DriverManager.getDriver().findElement(buttonLogin).click();
    }

    public void verifyLoginSuccess() {
//        Assert.assertFalse(DriverManager.getDriver().getCurrentUrl().contains("authentication"), "FAIL. Vẫn đang ở trang Login");
        WebUI.assertNotContains(WebUI.getCurrentUrl(), "authentication", "FAIL. Vẫn đang ở trang Login");
    }

    public void verifyLoginFail() {
        WebUI.assertContains(WebUI.getCurrentUrl(), "authentication", "FAIL. Vẫn đang ở trang Login");
        Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
//        Assert.assertEquals(DriverManager.getDriver().findElement(errorMessage).getText(), "Invalid email or password", "Content of error massage NOT match.");
        WebUI.assertEquals(WebUI.getElementText(errorMessage), "Invalid email or password", "Content of error massage NOT match.");
    }

    public void verifyLoginFail(String message) {
        WebUI.assertContains(WebUI.getCurrentUrl(), "authentication", "FAIL. Vẫn đang ở trang Login");
        Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
//        Assert.assertEquals(DriverManager.getDriver().findElement(errorMessage).getText(), message, "Content of error massage NOT match.");
        WebUI.assertEquals(WebUI.getElementText(errorMessage), message, "Content of error massage NOT match.");
    }

    public void verifyLoginFailWithNullFields() {
        WebUI.assertContains(WebUI.getCurrentUrl(), "authentication", "FAIL. Vẫn đang ở trang Login");
        Assert.assertTrue(WebUI.isElementDisplayed(errorMessage1), "Error message 1 NOT displays");
        Assert.assertTrue(WebUI.isElementDisplayed(errorMessage2), "Error message 2 NOT displays");

//        String actualMessage1 = DriverManager.getDriver().findElement(errorMessage1).getText();
//        String actualMessage2 = DriverManager.getDriver().findElement(errorMessage2).getText();

        WebUI.assertEquals(WebUI.getElementText(errorMessage1), "The Password field is required.", "Content of error massage 1 NOT match.");
        WebUI.assertEquals(WebUI.getElementText(errorMessage2), "The Email Address field is required.", "Content of error massage 2 NOT match.");
    }

    public void verifyLoginFailWithNullFields_ArrayList(int totalNullFields) {

        WebUI.assertContains(WebUI.getCurrentUrl(), "authentication", "FAIL. Vẫn đang ở trang Login");

        List<String> messageString = new ArrayList<>();
        messageString.add("The Password field is required.");
        messageString.add("The Email Address field is required.");

        boolean check = false;

        for (int i = 1; i <= totalNullFields; i++) {
            Assert.assertTrue(DriverManager.getDriver().findElement(By.xpath("//div[contains(@class, 'alert-danger')][" + i + "]")).isDisplayed(), "Error message " + i + " NOT displays");

            for (int j = 0; j < messageString.size(); j++) {
                if (WebUI.getElementText(By.xpath("//div[contains(@class, 'alert-danger')][" + i + "]")).equals(messageString.get(j))) {
                    check = true;
                    break;
                }
            }
            Assert.assertTrue(check, "The content of error message " + i + " not matched.");
        }
    }

    //Các hàm xử lý cho chính trang này
//    public void loginCRM(String email, String password) {
//        //https://crm.anhtester.com/admin/authentication

    /// /        DriverManager.getDriver().get("https://crm.anhtester.com/admin/authentication"); //Gọi từ class ConfigData dạng biến static
    /// /        setEmail(email);
    /// /        setPassword(password);
    /// /        clickLoginButton();
    /// /        Hoặc
    /// /        DriverManager.getDriver().findElement(inputEmail).sendKeys(email);
    /// /        DriverManager.getDriver().findElement(inputPassword).sendKeys(password);
    /// /        DriverManager.getDriver().findElement(buttonLogin).click();
//
//        WebUI.openURL("https://crm.anhtester.com/admin/authentication");
//        WebUI.setText(inputEmail, email);
//        WebUI.setText(inputPassword, password);
//        WebUI.clickElement(buttonLogin);
//    }
//    public void loginCRM() {
//        WebUI.openURL("https://crm.anhtester.com/admin/authentication");
//        WebUI.setText(inputEmail, "admin@example.com");
//        WebUI.setText(inputPassword, "123456");
//        WebUI.clickElement(buttonLogin);
//        verifyLoginSuccess();
//    }

    // Hàm này chỉ phục vụ cho việc test nội bộ trang Login, nên không cần liên kết trang
    public void loginCRM(String email, String password) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
        WebUI.waitForPageLoaded();
    }

    // Hàm này phục vụ cho việc Login để đi test những trang bên trong app, nên phải liên kết trang
    public DashboardPage loginCRM() throws InterruptedException {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.clearText(inputEmail);
        WebUI.clearText(inputPassword);
        WebUI.setText(inputEmail, "admin@example.com");
        WebUI.setText(inputPassword, "123456");
        WebUI.clickElement(buttonLogin);
        WebUI.waitForPageLoaded();
        Thread.sleep(1000);
        verifyLoginSuccess();

        return new DashboardPage();
    }
}
