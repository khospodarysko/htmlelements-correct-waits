package com.lohika.htmlelements.factory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementClassAnnotationsHandler;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementFieldAnnotationsHandler;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

public class ZeroTimeoutLocatorFactory implements CustomElementLocatorFactory {
    private final SearchContext searchContext;

    public ZeroTimeoutLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public ElementLocator createLocator(Field field) {
        return new MyAjaxElementLocator(this.searchContext, this.getTimeOut(field), new HtmlElementFieldAnnotationsHandler(field));
    }

    public ElementLocator createLocator(Class clazz) {
        return new MyAjaxElementLocator(this.searchContext, this.getTimeOut(clazz), new HtmlElementClassAnnotationsHandler(clazz));
    }

    public int getTimeOut(Field field) {
        // hard-coding timeout to 0 and using implicitlyWait instead
        return 0;
    }

    public int getTimeOut(Class clazz) {
        // hard-coding timeout to 0 and using implicitlyWait instead
        return 0;
    }
}
