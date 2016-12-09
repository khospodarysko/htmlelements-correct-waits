package com.lohika.htmlelements.factory;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.newInstance;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.InvocationTargetException;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

public class MyHtmlElementLoader extends HtmlElementLoader {
    public static void populatePageObject(Object page, SearchContext searchContext) {
        populatePageObject(page, new ZeroTimeoutLocatorFactory(searchContext));
    }

    public static void populatePageObject(Object page, CustomElementLocatorFactory locatorFactory) {
        PageFactory.initElements(new MyHtmlElementDecorator(locatorFactory), page);
    }

    public static <T extends HtmlElement> T createHtmlElement(Class<T> elementClass, WebElement elementToWrap, String name) {
        try {
            T instance = newInstance(elementClass);
            instance.setWrappedElement(elementToWrap);
            instance.setName(name);
            // Recursively initialize elements of the block
            populatePageObject(instance, elementToWrap);
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }
}
