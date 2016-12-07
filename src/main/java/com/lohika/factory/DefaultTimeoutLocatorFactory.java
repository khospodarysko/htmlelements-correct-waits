package com.lohika.factory;

import com.lohika.util.MyLog;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementClassAnnotationsHandler;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementFieldAnnotationsHandler;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

public class DefaultTimeoutLocatorFactory implements CustomElementLocatorFactory {
    private final SearchContext searchContext;

    public DefaultTimeoutLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public ElementLocator createLocator(Field field) {
        return new AjaxElementLocator(this.searchContext, this.getTimeOut(field), new HtmlElementFieldAnnotationsHandler(field));
    }

    public ElementLocator createLocator(Class clazz) {
        return new AjaxElementLocator(this.searchContext, this.getTimeOut(clazz), new HtmlElementClassAnnotationsHandler(clazz));
    }

    public int getTimeOut(Field field) {
        int timeout = field.isAnnotationPresent(Timeout.class) ?
            (field.getAnnotation(Timeout.class)).value()
            :
            (field.getGenericType() instanceof Class ?
                this.getTimeOut((Class) field.getGenericType())
                :
                this.getTimeOut((Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]));

        // TODO: hard-coding timeout to 0 and using implicitlyWait instead
        return 0;
    }

    public int getTimeOut(Class clazz) {
        int timeout = 0;
        try {
            Method method = Timeout.class.getMethod("value");
            do {
                if (clazz.isAnnotationPresent(Timeout.class)) {
                    timeout = (Integer) method.invoke(clazz.getAnnotation(Timeout.class));
                }
                clazz = clazz.getSuperclass();
            } while (clazz != Object.class && clazz != null);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            // ignore
        }

        // TODO: hard-coding timeout to 0 and using implicitlyWait instead
        return 0;
    }
}
