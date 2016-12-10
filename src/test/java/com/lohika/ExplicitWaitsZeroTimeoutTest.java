package com.lohika;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ExplicitWaitsZeroTimeoutTest extends BaseTest {
    @BeforeClass
    public void setUpTimeout() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void testElementTimeout() {
        printWaitTime(googlePage.nonExistentSearchInput, 5);
    }

    @Test
    public void testBlockElementTimeout() {
        printWaitTime(googlePage.noSearchForm.noSearchInput, 5);
    }
}

