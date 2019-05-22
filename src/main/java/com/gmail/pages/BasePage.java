package com.gmail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public void fillText(By elementBy, String text) {
        waitForElementBeVisible(elementBy);
        driver.findElement(elementBy).clear();
        driver.findElement(elementBy).sendKeys(text);
    }

    public void click(By elementBy) {
        waitForElementBeVisible(elementBy);
        driver.findElement(elementBy).click();
    }

    public String getText(By elementBy) {
        waitForElementBeVisible(elementBy);
        return driver.findElement(elementBy).getText();
    }

    public void waitForElementBeVisible(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public boolean isElementPresent(By element) {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementNotPresent(By element) {
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(element)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}