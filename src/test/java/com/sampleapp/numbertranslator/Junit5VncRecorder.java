package com.sampleapp.numbertranslator;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.TestDescription;

import java.io.File;
import java.util.Optional;

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

public class Junit5VncRecorder implements TestWatcher {

    @Container
    protected BrowserWebDriverContainer CHROME_CONTAINER = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions()).withRecordingMode(RECORD_ALL, new File("target"));;

    @Override
    public void testSuccessful(ExtensionContext context) {
        CHROME_CONTAINER.afterTest(toTestDescription(context), Optional.empty());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        CHROME_CONTAINER.afterTest(toTestDescription(context), Optional.of(cause));
    }

    private static TestDescription toTestDescription(ExtensionContext context) {
        return new TestDescription() {
            @Override
            public String getTestId() {
                return context.getDisplayName();
            }

            @Override
            public String getFilesystemFriendlyName() {
                return context.getTestInstance().get().getClass().getSimpleName() + "-" + context.getTestMethod().get().getName();
            }
        };
    }
}
