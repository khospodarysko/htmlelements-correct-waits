package com.lohika.pageobject.page;

import com.lohika.htmlelements.factory.MyHtmlElementDecorator;
import com.lohika.htmlelements.factory.ZeroTimeoutLocatorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(new MyHtmlElementDecorator(new ZeroTimeoutLocatorFactory(driver)), this);
        this.driver = driver;
    }
}