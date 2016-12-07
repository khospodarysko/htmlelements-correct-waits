package com.lohika.factory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementClassAnnotationsHandler;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementFieldAnnotationsHandler;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

public class NoTimeoutLocatorFactory implements CustomElementLocatorFactory {
    private final SearchContext searchContext;

    public NoTimeoutLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public ElementLocator createLocator(Field field) {
        return new DefaultElementLocator(this.searchContext, new HtmlElementFieldAnnotationsHandler(field));
    }

    public ElementLocator createLocator(Class clazz) {
        return new DefaultElementLocator(this.searchContext, new HtmlElementClassAnnotationsHandler(clazz));
    }
}
