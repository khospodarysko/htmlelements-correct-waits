package com.lohika.pageobject.google.original;

import com.lohika.pageobject.element.BaseElement;
import com.lohika.pageobject.page.OriginalBasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;

public class GooglePage extends OriginalBasePage {
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
