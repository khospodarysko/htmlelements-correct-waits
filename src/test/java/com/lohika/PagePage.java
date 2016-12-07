package com.lohika;

import com.lohika.element.SimpleTypifiedElement;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;

public class PagePage {
    @Name("Page Element")
    @FindBy(id = "emailAA")
    public SimpleTypifiedElement pageElement;

    public Block block;
}