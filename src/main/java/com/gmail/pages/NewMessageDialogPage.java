package com.gmail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class NewMessageDialogPage extends BasePage {

    public NewMessageDialogPage(WebDriver driver) {
        super(driver);
    }

    private By recipientsTextBox = By.name("to");
    private By subjectTextBox = By.name("subjectbox");
    private By messageBodyTextBox = By.xpath("//div[@aria-label='Message Body']");
    private By sendButton = By.xpath("//div[contains(@aria-label, 'Send') and @role='button']");

    public NewMessageDialogPage fillRecipients(String recipients) {
        fillText(recipientsTextBox, recipients);
        Reporter.log("New Message Dialog: fill 'Recipients' : " + recipients);
        return this;
    }

    public NewMessageDialogPage fillSubject(String subject) {
        Reporter.log("New Message Dialog: fill 'Subject' : " + subject);
        fillText(subjectTextBox, subject);
        return this;
    }

    public NewMessageDialogPage fillMessage(String message) {
        Reporter.log("New Message Dialog: fill 'Message' : " + message);
        fillText(messageBodyTextBox, message);
        return this;
    }

    public MainPage clickSend() {
        click(sendButton);
        Reporter.log("New Message Dialog: click 'Send'");
        return new MainPage(driver);
    }

    public MainPage sendMessage(String recipients, String subject, String message) {
        fillRecipients(recipients);
        fillSubject(subject);
        fillMessage(message);
        clickSend();
        return new MainPage(driver);
    }
}
