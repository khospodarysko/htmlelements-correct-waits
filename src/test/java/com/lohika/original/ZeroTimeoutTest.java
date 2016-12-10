package com.lohika.original;

import com.lohika.BaseTest;

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
        printAccessTime(originalGooglePage.searchInput);
    }

    @Test
    public void testNonExistentPageElement() {
        printAccessTime(originalGooglePage.nonExistentSearchInput);
    }

    @Test
    public void testNonExistentBlockElement() {
        printAccessTime(originalGooglePage.noSearchForm.noSearchInput);
    }
}

