package com.pages;

import com.microsoft.playwright.Page;
import com.qa.managers.FileReaderManager;

public class LoginPage {
    private Page page;
    private final String baseURL = FileReaderManager.getInstance().getConfigReader().getBaseUrl();


    private String topNavbar_login_menu = "//a[normalize-space()='Log in']";
    private String email_input = "//input[@id='Email']";
    private String password_input = "//input[@id='Password']";
    private String loginPage_login_btn = "button[type='submit']";
    private String topNavbar_my_account = "//a[@class='ico-account']";
    private String customerInfo_myAccount_title = "//h1[normalize-space()='My account - Customer info']";


    public LoginPage(Page page) {
        this.page = page;
    }

    public void gotoHomePage() {
        page.navigate(baseURL);
    }

    public void gotoLoginPage() {
        page.click(topNavbar_login_menu);
    }

    public String getLoginPageTitle() {
        String title = page.title();
        System.out.println("page title: " + title);
        return title;
    }

    public void inputLoginPageEmail(String email) {
        page.fill(email_input, email);
    }

    public void inputLoginPagePassword(String password) {
        page.fill(password_input, password);
    }

    public void clickLoginPageLoginButton() {
        page.click(loginPage_login_btn);
    }

    public void clickHomePageMyAccountMenu() {
        page.click(topNavbar_my_account);
    }

    public String getMyAccountPageHeaderTitle(){
        return page.textContent(customerInfo_myAccount_title);
    }

}
