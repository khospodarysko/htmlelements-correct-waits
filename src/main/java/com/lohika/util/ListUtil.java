package com.lohika.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListUtil {
    WebDriver driver;
    List<WebElement> tiles;

    /**
     * Open application
     *
     * @param name Application name
     */
    public void openApplication(String name) {
        if (isListStable(tiles)) {
            for (WebElement tile : tiles) {
                if (name.equals(tile.getText())) {
                    MyLog.log("Click on application tile " + name);
                    tile.click();
                    return;
                }
            }
            throw new IllegalStateException("Application list did not have '" + name + "'");
        } else {
            throw new IllegalStateException("Application list was not fully loaded");
        }
    }

    private void clickApplication(String name) {
        driver.findElement(By.xpath("//div[@class='name-container']/span[text()='" + name + "']")).click();
    }

    //TODO Check if it's true
    /*
    Such approach could be used when it is really needed to search for some item in list by dynamic text.
    In other cases if text is known in advance it's much easier to map it to @FindBy element and let WebDriver
    to deal with timeouts while searching it.
    And it is easier not to pass strings/enums to method but to have properly named page object.

    Will work fine wit @FindBy lists only as lists found by WebDriver.findElements() are not searched every time
    new item appears on the page that belongs to that list so most likely Stale exception will be thrown.
     */
    public boolean isListStable(List<WebElement> list) {
        int n1 = list.size();
        Wait.miliseconds(150);
        int n2 = list.size();
        MyLog.log("before 1 " + n1 + " " + n2);
        if (n1 == n2) {
            Wait.miliseconds(150);
            n1 = list.size();
            MyLog.log("before 2 " + n1 + " " + n2);
            if (n1 == n2) {
                Wait.miliseconds(150);
                n2 = list.size();
                MyLog.log("before 3 " + n1 + " " + n2);
                if (n1 == n2) {
                    if (n1 != 0) {
                        MyLog.log("stable");
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    MyLog.log("in 3 " + n1 + " " + n2);
                }
            } else {
                MyLog.log("in 2 " + n1 + " " + n2);
            }
        } else {
            MyLog.log("in 1 " + n1 + " " + n2);
        }
        return false;
    }
}
