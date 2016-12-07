package com.lohika;

import com.lohika.block.SimpleHtmlElement;
import com.lohika.element.SimpleTypifiedElement;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;

@Name("Block in Block")
@FindBy(id = "pwandloginareaAA")
public class BlockInBlock extends SimpleHtmlElement {
    @Name("Element in a Block in Block")
    @FindBy(id = "passwordAA")
    public SimpleTypifiedElement blockInBlockElement;
}
