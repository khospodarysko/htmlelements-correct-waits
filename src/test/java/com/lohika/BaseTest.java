package com.lohika;

import com.lohika.pageobject.google.GooglePage;
import com.lohika.util.MyLog;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public WebDriver driver;
    public GooglePage googlePage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-2.25");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");

        driver = new ChromeDriver();

        googlePage = new GooglePage(driver);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://www.google.com");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void printAccessTime(WebElement element) {
        long start = System.currentTimeMillis();
        try {
            element.getTagName();
        } catch (Exception ex) {
            // ignore
        }
        MyLog.log((System.currentTimeMillis() - start));
    }

    public void printWaitTime(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        long start = System.currentTimeMillis();
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            // ignore
        }
        MyLog.log((System.currentTimeMillis() - start));
    }
}
