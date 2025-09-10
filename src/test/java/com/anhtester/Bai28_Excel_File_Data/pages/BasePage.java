package com.anhtester.Bai28_Excel_File_Data.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class BasePage {

    // Dùng public do đây là class chung
    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuCustomer = By.xpath("//span[normalize-space()='Customers']");
    public By iconProfile = By.xpath("//li[contains(@class,'header-user-profile')]");
    public By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By menuSales = By.xpath("//span[@class='menu-text' and normalize-space()='Sales']");
    public By menuProposals = By.xpath("//span[normalize-space()='Proposals']");

    public CustomerPage clickMenuCustomers() {
        WebUI.waitForElementVisible(menuCustomer);
        WebUI.clickElement(menuCustomer);
        return new CustomerPage();
    }
}
