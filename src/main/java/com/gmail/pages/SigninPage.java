package com.gmail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import utils.LoadProperties;

public class SigninPage extends BasePage {

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    private String baseUrl = LoadProperties.load("baseUrl");

    private By emailPhoneTextBox = By.xpath("//input[@type='email']");
    private By passwordTextBox = By.xpath("//input[@type='password']");
    private By nextEmailPhoneButton = By.xpath("//div[@id='identifierNext']//span");
    private By nextPasswordButton = By.xpath("//div[@id='passwordNext']//span");

    public SigninPage goSigninPage() {
        driver.get(baseUrl);
        Reporter.log("Open Sign In Page: " + baseUrl);
        return this;
    }

    public SigninPage fillEmail(String email) {
        fillText(emailPhoneTextBox, email);
        Reporter.log("'Sign in' page: fill Email: " + email);
        return this;
    }

    public SigninPage clickNextEmailPhone() {
        click(nextEmailPhoneButton);
        Reporter.log("'Sign in' page: click 'Next' button");
        return this;
    }

    public SigninPage fillPassword(String password) {
        fillText(passwordTextBox, password);
        Reporter.log("'Sign in' page: fill password: " + password);
        return this;
    }

    public SigninPage clickNextPassword() {
        click(nextPasswordButton);
        Reporter.log("'Sign in' page: click 'Next' button");
        return this;
    }

    public MainPage signinToGmail(String email, String password) {
        fillEmail(email);
        clickNextEmailPhone();
        fillPassword(password);
        clickNextPassword();
        return new MainPage(driver);
    }
}