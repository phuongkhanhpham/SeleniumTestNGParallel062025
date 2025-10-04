package com.anhtester.HandleUploadFile;

import com.anhtester.common.BaseTest;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class HandleUploadFile extends BaseTest {

    @Test
    public void testUploadFileWithSendkeys() {
        DriverManager.getDriver().get("https://the-internet.herokuapp.com/upload");
        WebUI.openURL("https://the-internet.herokuapp.com/upload");
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);
        By inputFileUpload = By.xpath("//input[@id='file-upload']");

        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\image2.jpg";
        WebUI.setText(inputFileUpload, filePath);
//        DriverManager.getDriver().findElement(inputFileUpload).sendKeys(filePath);
        WebUI.sleep(2);
        WebUI.clickElement(By.xpath("//input[@id='file-submit']"));
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(By.xpath("//h3[text()='File Uploaded!']")), "Can not upload file.");
    }

    @Test
    public void testUploadFileWithRobotClass() {
        DriverManager.getDriver().get("https://files.fm/");

        WebUI.sleep(2);

        By textOnPage = By.xpath("//div[@id='file_select_dragndrop_text']");
        By divFileUpload = By.xpath("//div[@id='uploadifive-file_upload']");
        By inputFileUpload = By.xpath("//div[@id='file_select_button']//input[@id='file_upload']");

//        String filePath = "C:\\Users\\Khanh\\Downloads\\playwright-ts-demo-master\\playwright-ts-demo-master\\node_modules\\playwright-core\\lib\\server\\chromium\\appIcon.png";
        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\image2.jpg";

        WebUI.uploadFileWithRobotClass(divFileUpload, filePath);

        // Verify upload file successfully

        By fileNameAfterUploadSuccess = By.xpath("//span[@class='filename']");
        Assert.assertTrue(WebUI.checkElementExist(fileNameAfterUploadSuccess), "Can not upload file.");
        WebUI.assertEquals(WebUI.getElementText(fileNameAfterUploadSuccess), "image2.jpg", "The file name is not matched.");
    }
}
