package com.anhtester.Bai28_Excel_File_Data.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardPage extends BasePage {

    private By totalInvoicesAwaitingPayment = By.xpath("//span[normalize-space()='Invoices Awaiting Payment']/parent::div/following-sibling::span");
    private By totalConvertedLeads = By.xpath("//span[normalize-space()='Invoices Awaiting Payment']/parent::div/following-sibling::span");

    public void verifyInvoicesAwaitingPayment(String total) {
        Assert.assertTrue(WebUI.isElementDisplayed(totalInvoicesAwaitingPayment), "The Invoices Awaiting Payment total label is not displayed.");
        Assert.assertEquals(WebUI.getElementText(totalInvoicesAwaitingPayment), total, "The Invoices Awaiting Payment total is not matched.");
    }
}
