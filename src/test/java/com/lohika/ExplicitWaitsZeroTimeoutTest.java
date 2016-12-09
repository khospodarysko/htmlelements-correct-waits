package com.lohika;

import com.lohika.util.MyLog;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ExplicitWaitsZeroTimeoutTest extends BaseTest {
    @Test
    public void testTimeoutLess() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        long start = System.currentTimeMillis();
        try {
            wait.until(ExpectedConditions.visibilityOf(googlePage.nonExistentSearchInput));
        } catch (TimeoutException e) {
            // ignore
        }
        MyLog.log((System.currentTimeMillis() - start));
    }

    @Test
    public void testTimeoutGreater() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        long start = System.currentTimeMillis();
        try {
            wait.until(ExpectedConditions.visibilityOf(googlePage.nonExistentSearchInput));
        } catch (TimeoutException e) {
            // ignore
        }
        MyLog.log((System.currentTimeMillis() - start));
    }

    @Test
    public void testTimeoutEqual() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        long start = System.currentTimeMillis();
        try {
            wait.until(ExpectedConditions.visibilityOf(googlePage.nonExistentSearchInput));
        } catch (TimeoutException e) {
            // ignore
        }
        MyLog.log((System.currentTimeMillis() - start));
    }
}

