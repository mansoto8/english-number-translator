package com.sampleapp.numbertranslator.e2e;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerE2E {

    @LocalServerPort
    private int port;

    @Container
    public BrowserWebDriverContainer container = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions()).withRecordingMode(RECORD_ALL, new File("target"));

    private RemoteWebDriver driver;

    @BeforeEach
    public void setup() {
        driver = container.getWebDriver();
    }

    @Test
    public void shouldTranslateAndShowHistory() throws InterruptedException {

        // Check translation and history retrieval
        driver.get("http://host.docker.internal:" + port);
        driver.findElementById("number-input").sendKeys("1234");
        driver.findElementById("translate-button").click();
        new WebDriverWait((WebDriver) driver, 3)
                .until(ExpectedConditions.textToBePresentInElementValue(By.id("translation-output"),
                        "One thousand two hundred and thirty four"));
        driver.findElementById("history-button").click();
        new WebDriverWait((WebDriver) driver, 3)
                .until(ExpectedConditions.textToBePresentInElement(
                        driver.findElementByXPath("//*[@id=\"history-table\"]/tbody/tr/td[1]"),
                        "1,234"));
        WebElement translation =
                driver.findElementByXPath("//*[@id=\"history-table\"]/tbody/tr/td[2]");
        assertEquals("One thousand two hundred and thirty four", translation.getText());

        // Check errors when input is wrong
        driver.findElementById("number-input").sendKeys("dafds");
        driver.findElementById("translate-button").click();

        new WebDriverWait((WebDriver) driver, 3)
                .until(ExpectedConditions.textToBePresentInElement(driver.findElementById("formFeedBackNumber"),
                        "Incorrect number format"));

        driver.findElementById("number-input").clear();
        driver.findElementById("number-input").sendKeys("99999999999");
        driver.findElementById("translate-button").click();

        new WebDriverWait((WebDriver) driver, 3)
                .until(ExpectedConditions.textToBePresentInElement(driver.findElementById("formFeedBackNumber"),
                        "Number is too big. Max allowed is +/- 9,999,999,999"));


    }
}
