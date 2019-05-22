package com.gmail.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static Logger log = Logger.getLogger(MainPage.class);

    private By composeButton = By.xpath("//*[@role='button' and @gh='cm']");
    private By deleteButton = By.xpath("//*[@class='T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs mA']");
    private By inboxLink = By.xpath("//a[@title='Inbox']");
    private By starredLink = By.xpath("//a[@title='Starred']");
    private static final String TOOLTIP_XPATH = "//*[@class='bAq']";
    private static final String LETTER_CHECKBOX_XPATH = "//span[text()='%s']/ancestor::tr/td[2]";


    public MainPage waitLetter(String subject) {
        Reporter.log("Main page: wait letter with subject: " + subject);
        WebElement element = null;
        int counter = 0;
        while (element == null && counter != 20) {
            try {
                log.info("Waiting letter with subject: '" + subject + "'...." + counter);
                clickInboxLink();
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//*[@class='bog']/span[text()='%s']", subject))));
            } catch (TimeoutException te) {
                log.info("Not received yet, continuing");
                counter++;
            }
        }
        return this;
    }

    public MainPage waitLetterRemoved(String subject) {
        Reporter.log("Main page: wait letter with subject: '" + subject + "' be removed");
        boolean removed = false;
        int counter = 0;
        while (removed == false && counter != 20) {
            try {
                log.info("Waiting letter removed: '" + subject + "'...." + counter);
                clickInboxLink();
                removed = isElementNotPresent((By.xpath(String.format(LETTER_CHECKBOX_XPATH, subject))));
            } catch (TimeoutException te) {
                log.info("Not removed yet, continuing");
                counter++;
            }
        }
        return this;
    }

    public NewMessageDialogPage clickCompose() {
        click(composeButton);
        Reporter.log("Main page: click 'Compose' button");
        return new NewMessageDialogPage(driver);
    }

    public MainPage clickInboxLink() {
        Reporter.log("Main page: click 'Inbox' link");
        click(inboxLink);
        return this;
    }

    public MainPage clickStarredLink() {
        Reporter.log("Main page: click 'Starred' link");
        click(starredLink);
        return this;
    }

    public MainPage clickDelete() {
        Reporter.log("Main page: click 'Delete' button");
        click(deleteButton);
        return this;
    }

    public MainPage selectLetter(String subject) {
        Reporter.log("Main page: select letter with subject: " + subject);
        click(By.xpath(String.format(LETTER_CHECKBOX_XPATH, subject)));
        return this;
    }

    public boolean isLetterNotPresent(String subject) {
        return isElementNotPresent(By.xpath(String.format(LETTER_CHECKBOX_XPATH, subject)));
    }

    public boolean isLetterPresent(String subject) {
        return isElementPresent(By.xpath(String.format(LETTER_CHECKBOX_XPATH, subject)));
    }

    public boolean isComposeButtonPresent() {
        return isElementPresent(composeButton);
    }

    public boolean waitMessageSent() {
        Reporter.log("Main page: wait tooltip 'Message sent'");
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(TOOLTIP_XPATH), "Message sent."));
    }

    public boolean waitMessageRemoved() {
        Reporter.log("Main page: wait tooltip 'Conversation moved to Trash'");
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(TOOLTIP_XPATH), "Conversation moved to Trash."));
    }
}