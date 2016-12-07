package com.lohika.factory;

import com.lohika.util.MyLog;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.SystemClock;

import java.util.concurrent.TimeUnit;

public class AjaxElementLocator extends DefaultElementLocator {
    private final int timeOutInSeconds;
    private final Clock clock;

    // for debugging
    private By by;
    private SearchContext searchContext;

    public AjaxElementLocator(SearchContext context, int timeOutInSeconds, AbstractAnnotations annotationsHandler) {
        this(new SystemClock(), context, timeOutInSeconds, annotationsHandler);
    }

    public AjaxElementLocator(Clock clock, SearchContext context, int timeOutInSeconds, AbstractAnnotations annotationsHandler) {
        super(context, annotationsHandler);
        this.timeOutInSeconds = timeOutInSeconds;
        this.clock = clock;

        this.by = annotationsHandler.buildBy();
        this.searchContext = context;
    }

    @Override
    public WebElement findElement() {
        SlowLoadingElement loadingElement = new SlowLoadingElement(clock, timeOutInSeconds);
        try {
            return loadingElement.get().getElement();
        } catch (NoSuchElementError e) {
            String causeMessage = e.getCause().getMessage();
            if (causeMessage.contains("\n")) {
                causeMessage = causeMessage.substring(0, causeMessage.indexOf("\n"));
            }
            throw new NoSuchElementException(
                String.format("Timed out after %d seconds. %s (%s)", timeOutInSeconds, e.getMessage(), causeMessage),
                e.getCause());
        }
    }

    /**
     * Could be overridden to provide custom sleep time
     *
     * @return sleep time
     */
    protected long sleepFor() {
        return 1000L;
    }

    /**
     * Method to decide when element is usable for interaction. Be default Selenium can use element that is present in
     * DOM, leaving this to return true behaves as Selenium. element.isDisplayed could be used to unsure visibility of
     * element. Usually it is the case but in rare cases we need to get some properties from not visible elements
     * that are therefore present in DOM.
     *
     * @param element Element to check
     * @return true is check is passed
     */
    protected boolean isElementUsable(WebElement element) {
        return true;
    }

    private class SlowLoadingElement extends SlowLoadableComponent<SlowLoadingElement> {
        private WebElement element;
        private final Clock clock;
        private final long timeOutInSeconds;

        public SlowLoadingElement(Clock clock, int timeOutInSeconds) {
            super(clock, timeOutInSeconds);
            this.clock = clock;
            this.timeOutInSeconds = (long)timeOutInSeconds;
        }

        @Override
        public SlowLoadingElement get() {
            // do not loop and return immediately as our custom locator does not use predefined timeout
            this.isLoaded();
            return this;
        }

        @Override
        protected void load() {
        }

        @Override
        protected long sleepFor() {
            return AjaxElementLocator.this.sleepFor();
        }

        @Override
        protected void isLoaded() throws Error {
            try {

                /*
                 * Recursion happens here.
                 * For block-in-block cases assume here is the code
                 *   element = AjaxElementLocator.this.searchContext.findElement(AjaxElementLocator.this.by);
                 * so whenever searchContext.findElement is called it mean invoke is called for searchContext's Proxy
                 * and it means for case of AjaxElementLocator that SlowLoadingElement.get() is called.
                 * And it goes recursively up to that searchContext that is found or up to the whole chain of
                 * element-in-block-in-block ...
                 * That means it does not wait for default timeout, like 5 seconds but there is 5 seconds timeout
                 * for each link in the chain and it depends on the structure of page / block objects
                 */
                element = AjaxElementLocator.super.findElement();
                if (!isElementUsable(element)) {
                    throw new NoSuchElementException("Element is not usable");
                }
            } catch (NoSuchElementException e) {
                throw new NoSuchElementError("Unable to locate the element", e);
            }
        }

        public WebElement getElement() {
            return element;
        }
    }

    private static class NoSuchElementError extends Error {
        private NoSuchElementError(String message) {
            super(message);
        }

        private NoSuchElementError(String message, Throwable throwable) {
            super(message, throwable);
        }
    }
}