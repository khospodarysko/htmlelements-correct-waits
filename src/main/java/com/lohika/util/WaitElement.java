package com.lohika.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitElement {
    // TODO Get WebDriver from ThreadLocal and do not pass it to method
    public static WebElement visible(WebDriver driver, By by) {
        WebDriverWait visibleWait = new WebDriverWait(driver, 10);
        return visibleWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
