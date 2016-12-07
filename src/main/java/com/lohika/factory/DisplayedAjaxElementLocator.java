package com.lohika.factory;

import com.lohika.util.MyLog;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.SystemClock;

public class DisplayedAjaxElementLocator extends DefaultElementLocator {
    protected final int timeOutInSeconds;
    private final Clock clock;

    public DisplayedAjaxElementLocator(SearchContext context,
                                       int timeOutInSeconds,
                                       AbstractAnnotations annotationsHandler) {
        this(new SystemClock(), context, timeOutInSeconds, annotationsHandler);
    }

    public DisplayedAjaxElementLocator(Clock clock,
                                       SearchContext context,
                                       int timeOutInSeconds,
                                       AbstractAnnotations annotationsHandler) {
        super(context, annotationsHandler);
        this.timeOutInSeconds = timeOutInSeconds;
        this.clock = clock;
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

    protected long sleepFor() {
        // TODO magic number
        return 500L;
    }

    protected boolean isElementUsable(WebElement element) {
        return element.isDisplayed();
    }

    private class SlowLoadingElement extends SlowLoadableComponent<SlowLoadingElement> {
        private WebElement element;

        public SlowLoadingElement(Clock clock, int timeOutInSeconds) {
            super(clock, timeOutInSeconds);
        }

        // TODO in AjaxElementLocator use own SlowLoading with proper while loop in get()
        @Override
        public SlowLoadingElement get() {
            return super.get();
        }

        @Override
        protected void load() {
        }

        @Override
        protected long sleepFor() {
            return DisplayedAjaxElementLocator.this.sleepFor();
        }

        @Override
        protected void isLoaded() throws Error {
            try {
                element = DisplayedAjaxElementLocator.super.findElement();
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
        private NoSuchElementError(String message, Throwable throwable) {
            super(message, throwable);
        }
    }
}