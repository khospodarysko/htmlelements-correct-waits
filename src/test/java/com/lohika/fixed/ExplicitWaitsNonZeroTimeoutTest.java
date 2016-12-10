package com.lohika.fixed;

import com.lohika.BaseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ExplicitWaitsNonZeroTimeoutTest extends BaseTest {
    @BeforeClass
    public void setUpTimeout() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testTimeoutLess() {
        printWaitTime(googlePage.nonExistentSearchInput, 5);
    }

    /**
     * Executes to 20 seconds, as:
     * - 1st time it wait implicitly for 10 seconds
     * - loop is not finished yet as timeout for loop is 15
     * - 2nd wait for 10 seconds
     * - check if loop is timed out and it is - stop
     */
    @Test
    public void testTimeoutGreater() {
        printWaitTime(googlePage.nonExistentSearchInput, 15);
    }

    @Test
    public void testTimeoutEqual() {
        printWaitTime(googlePage.nonExistentSearchInput, 10);
    }

    @Test
    public void testBlockElementTimeoutGreater() {
        printWaitTime(googlePage.noSearchForm.noSearchInput, 15);
    }

    @Test
    public void testBlockElementTimeoutLess() {
        printWaitTime(googlePage.noSearchForm.noSearchInput, 5);
    }
}

