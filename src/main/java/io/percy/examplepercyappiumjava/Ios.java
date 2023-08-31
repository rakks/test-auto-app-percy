package io.percy.examplepercyappiumjava;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import io.percy.appium.AppPercy;

public class Ios {
    private static AppPercy percy;

    // Hub Url to connect to Automation session
    private static String HUB_URL = "https://hub.browserstack.com/wd/hub";

    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Browserstack specific capabiilities
        capabilities.setCapability("browserstack.user", "rakshitkhurana_X0nAGC");
        capabilities.setCapability("browserstack.key", "o7LsagUzshEPjVnze2me");
        capabilities.setCapability("browserstack.appium_version", "1.20.2");

        // Percy Options
        capabilities.setCapability("percy.enabled", "true");
        capabilities.setCapability("percy.ignoreErrors", "true");

        // App url we get post uploading in response
        capabilities.setCapability("app", "bs://469d045fe71b0b013ec515342df81f0c0d2749dd");
        capabilities.setCapability("device", "iPhone 14");
        capabilities.setCapability("os_version", "16");
        capabilities.setCapability("project", "First Java Project");

        // Create sessioin
        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL(HUB_URL), capabilities);

        // Initialize AppPercy
        percy = new AppPercy(driver);

        // Take First Screenshot
        percy.screenshot("First Screenshot");

        // Find element and click to change screen
        IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
        textButton.click();

        // Find textInput and send some data to it
        IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
        textInput.sendKeys("changedemail11@browserstack.com");

        // Take Second Screenshot Post screen update
        percy.screenshot("Second Screenshot");

        percy.screenshot("Tenth Screenshot");
        driver.quit();
    }
}
