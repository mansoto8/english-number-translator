package com.sampleapp.numbertranslator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerIT {

    @LocalServerPort
    private int port;

    @Test
    public void shouldDisplayMessage() {
        // depending on your operation system try 172.17.0.1 as IP or container.getTestHostIpAddress()
        this.container.getWebDriver().get("http://host.docker.internal:" + port);
        WebElement messageElement = this.container.getWebDriver().findElementById("translation-output-label");
        assertEquals("The number translated is:", messageElement.getText());
    }
}
