package com.lohika;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ZeroTimeoutTest extends BaseTest {
    @BeforeClass
    public void setUpTimeout() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void testPageElement() {
        printAccessTime(googlePage.searchInput);
    }

    @Test
    public void testNonExistentPageElement() {
        printAccessTime(googlePage.nonExistentSearchInput);
    }

    @Test
    public void testNonExistentBlockElement() {
        printAccessTime(googlePage.noSearchForm.noSearchInput);
    }
}

