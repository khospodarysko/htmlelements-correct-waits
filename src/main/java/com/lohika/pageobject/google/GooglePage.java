package com.lohika.pageobject.google;

import com.lohika.pageobject.element.BaseElement;
import com.lohika.pageobject.page.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;

public class GooglePage extends BasePage {
    @Name("Search Input")
    @FindBy(name = "q")
    public BaseElement searchInput;

    @Name("Non Existent Search Input")
    @FindBy(name = "qqq")
    public BaseElement nonExistentSearchInput;

    public NoSearchForm noSearchForm;

    public GooglePage(WebDriver driver) {
        super(driver);
    }
}
