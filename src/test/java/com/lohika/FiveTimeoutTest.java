package com.lohika;

import com.lohika.util.MyLog;

import org.testng.annotations.Test;

public class FiveTimeoutTest extends BaseTest {
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

