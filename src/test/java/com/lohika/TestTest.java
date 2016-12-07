package com.lohika;

import com.lohika.factory.DefaultTimeoutLocatorFactory;
import com.lohika.factory.MyHtmlElementDecorator;
import com.lohika.util.MyLog;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

public class TestTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-2.25");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test_1() {
        // driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        PagePage page = new PagePage();
        PageFactory.initElements(new MyHtmlElementDecorator(new DefaultTimeoutLocatorFactory(driver)), page);

        long start;

        start = System.currentTimeMillis();
        try {
            page.pageElement.getTagName();
        } catch (Exception ex) {
            // ignore
        } finally {
            MyLog.log("page element: " + (System.currentTimeMillis() - start));
        }

        start = System.currentTimeMillis();
        try {
            page.block.blockInBlock.blockInBlockElement.getText();
        } catch (Exception ex) {
            // ignore
        } finally {
            MyLog.log("page block in block element: " + (System.currentTimeMillis() - start));
        }

        start = System.currentTimeMillis();
        try {
            // TODO How to make driver to return Proxy web element as in PageFactory?
            driver.findElement(By.id("emailAA"));
        } catch (Exception ex) {
            // ignore
        } finally {
            MyLog.log("direct find: " + (System.currentTimeMillis() - start));
        }

        WebDriverWait wait = new WebDriverWait(driver, 10);
        start = System.currentTimeMillis();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("123")));
        } catch (Exception e) {
            MyLog.log("webdriverwait time: " + (System.currentTimeMillis() - start));
        }
    }

    // TODO: Negative search when implicit wait is set
    // switch off/on timeout for driver in negative waiter method
}

