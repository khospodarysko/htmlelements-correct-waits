package com.lohika;

import com.lohika.block.SimpleHtmlElement;
import com.lohika.element.SimpleTypifiedElement;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;

@Name("Block")
@FindBy(id = "login-formAA")
public class Block extends SimpleHtmlElement {
    @Name("Element in block")
    @FindBy(id = "emailAA")
    public SimpleTypifiedElement blockElement;

    public BlockInBlock blockInBlock;
}
