package com.lohika.pageobject.google;

import com.lohika.pageobject.block.BaseBlock;
import com.lohika.pageobject.element.BaseElement;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;

@Name("Search Form")
@FindBy(id = "sfdiv123")
public class NoSearchForm extends BaseBlock {
    @Name("Search Input")
    @FindBy(name = "q123")
    public BaseElement noSearchInput;
}
