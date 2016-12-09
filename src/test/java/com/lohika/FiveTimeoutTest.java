package com.lohika;

import com.lohika.pageobject.google.GooglePage;
import com.lohika.util.MyLog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FiveTimeoutTest {
    private WebDriver driver;
    private GooglePage googlePage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-2.25");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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

    @Test
    public void testPageElement() {
        long start = System.currentTimeMillis();
        try {
            googlePage.searchInput.getTagName();
        } catch (Exception ex) {
            // ignore
        } finally {
            MyLog.log((System.currentTimeMillis() - start));
        }
    }

    @Test
    public void testNonExistentPageElement() {
        long start = System.currentTimeMillis();
        try {
            googlePage.nonExistentSearchInput.getTagName();
        } catch (Exception ex) {
            // ignore
        } finally {
            MyLog.log((System.currentTimeMillis() - start));
        }
    }
}

