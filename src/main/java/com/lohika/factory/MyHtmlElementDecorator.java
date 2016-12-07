package com.lohika.factory;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.getElementName;

import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

public class MyHtmlElementDecorator extends HtmlElementDecorator {
    public MyHtmlElementDecorator(CustomElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    protected <T extends HtmlElement> T decorateHtmlElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = decorateWebElement(loader, field);
        return MyHtmlElementLoader.createHtmlElement((Class<T>) field.getType(), elementToWrap, getElementName(field));
    }
}
