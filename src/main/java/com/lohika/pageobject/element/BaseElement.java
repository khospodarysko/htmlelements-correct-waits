package com.lohika.pageobject.element;

import com.lohika.util.MyLog;
import com.lohika.util.Wait;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class BaseElement extends TypifiedElement {
    public BaseElement(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void click() {
        try {
            super.click();
        } catch (WebDriverException e) {
            Wait.miliseconds(500);
            String message = e.getMessage();
            if (message.contains("\n")) {
                message = message.substring(0, message.indexOf("\n"));
            }
            MyLog.log("Click again due to: " + message);
            super.click();
        }
    }
}
