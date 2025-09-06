package com.anhtester.common;

public class LocatorCRM {

    //Login page
    public static String headerLogin = "//h1[normalize-space()='Login']";
    public static String inputEmail = "//input[@id='email']";
    public static String inputPassword = "//input[@id='password']";
    public static String checkboxRememberMe = "//input[@id='remember']";
    public static String buttonLogin = "//button[@type='submit']";
    public static String linkForgotPassword = "//a[normalize-space()='Forgot Password?']";
    public static String alertErrorMessage = "//div[@id='alerts']/div";

    //Dashboard page
    public static String menuDashboard = "//span[normalize-space()='Dashboard']";
    public static String menuCustomer = "//span[normalize-space()='Customers']";
    public static String iconProfile = "//li[contains(@class,'header-user-profile')]";
    public static String menuTasks = "//span[normalize-space()='Tasks']";
    public static String menuProjects = "//span[normalize-space()='Projects']";
    public static String menuSales = "//span[@class='menu-text' and normalize-space()='Sales']";
    public static String menuProposals = "//span[normalize-space()='Proposals']";

    public static String totalInvoicesAwaitingPayment = "(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span";
    public static String totalConvertedLeads = "(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span";
    public static String totalProjectsInProgress = "(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span";
    public static String totalTasksNotFinished = "(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span";

    //Tasks page
    public static String buttonNewTask = "//a[normalize-space()='New Task']";

    // Sales/Proposals page
    public static String buttonNewProposal = "//a[normalize-space()='New Proposal']";

    //Customers page
    public static String headerCustomersPage = "//span[normalize-space()='Customers Summary']";
    public static String buttonAddNewCustomer = "//a[normalize-space()='New Customer']";
    public static String buttonImportCustomer = "//a[normalize-space()='Import Customers']";
    public static String searchCustomer = "//div[@id='clients_filter']//input[@type='search']";
    public static String itemCustomerFirst = "//table[@id='clients']//tbody/tr[1]/td[3]/a";
    //Add new customer page
    public static String inputCompany = "//input[@id='company']";
    public static String inputVat = "//input[@id='vat']";
    public static String inputPhoneNumber = "//input[@id='phonenumber']";
    public static String inputWebsite = "//input[@id='website']";
    public static String dropdownGroups = "//button[@data-id='groups_in[]']";
    public static String inputSearchGroups = "//button[@data-id='groups_in[]']/following-sibling::div//input";
    public static String itemVIP = "//span[text()='VIP']";
    public static String dropdownLanguage = "//button[@data-id='default_language']";
    public static String itemVietnamese = "//span[normalize-space()='Vietnamese']";
    public static String inputAddress = "//textarea[@id='address']";
    public static String inputCity = "//input[@id='city']";
    public static String inputState = "//input[@id='state']";
    public static String inputZipCode = "//input[@id='zip']";
    public static String dropdownCountry = "//button[@data-id='country']";
    public static String inputsearchCountry = "//button[@data-id='country']/following-sibling::div//input";
    public static String itemVietnamCountry = "//span[text()='Vietnam']";
    public static String buttonSave = "//div[@id='profile-save-section']/button[normalize-space()='Save']";
}
