package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DemoAppTest {

    @Test
    public void withOutPageFactory() {

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .autoGrantPermissions()
                .setFullReset(false)
                .setNoReset(true)
                .setApp(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Android-MyDemoAppRN.1.3.0.build-244.apk").toString())
                .setAppPackage("com.saucelabs.mydemoapp.rn")
                .setAppActivity("com.saucelabs.mydemoapp.rn.MainActivity");

        URL url;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        AppiumDriver originalAppiumDriver = new AndroidDriver(url, uiAutomator2Options);

        WebDriverListener webDriverListener = new DemoWebDriverListener();
        WebDriver decoratedAppiumDriver = new EventFiringDecorator<>(webDriverListener).decorate(originalAppiumDriver);

        By locatorNavOpenMenu = AppiumBy.accessibilityId("open menu");
        WebElement webElementNavOpenMenu = decoratedAppiumDriver.findElement(locatorNavOpenMenu);
        webElementNavOpenMenu.click();

        By locatorMenuItemLogIn = AppiumBy.accessibilityId("menu item log in");
        WebElement webElementMenuItemLogIn = decoratedAppiumDriver.findElement(locatorMenuItemLogIn);
        webElementMenuItemLogIn.click();

        By locatorTxtUserName = AppiumBy.accessibilityId("Username input field");
        WebElement webElementTxtUserName = decoratedAppiumDriver.findElement(locatorTxtUserName);
        webElementTxtUserName.sendKeys("bob@example.com");

        By locatorPwdPassword = AppiumBy.accessibilityId("Password input field");
        WebElement webElementPwdPassword = decoratedAppiumDriver.findElement(locatorPwdPassword);
        webElementPwdPassword.sendKeys("10203040");
    }

    @AndroidFindBy(accessibility = "open menu")
    private WebElement webElementNavOpenMenu1;
    @AndroidFindBy(accessibility = "menu item log in")
    private WebElement webElementMenuItemLogIn1;
    @AndroidFindBy(accessibility = "Username input field")
    private WebElement webElementTxtUserName1;
    @AndroidFindBy(accessibility = "Password input field")
    private WebElement webElementPwdPassword1;
    @AndroidFindBy(accessibility = "Login button")
    private WebElement webElementBtnLogin1;

    @Test
    public void withPageFactory() {

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .autoGrantPermissions()
                .setFullReset(false)
                .setNoReset(true)
                .setApp(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Android-MyDemoAppRN.1.3.0.build-244.apk").toString())
                .setAppPackage("com.saucelabs.mydemoapp.rn")
                .setAppActivity("com.saucelabs.mydemoapp.rn.MainActivity");

        URL url;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        AppiumDriver originalAppiumDriver = new AndroidDriver(url, uiAutomator2Options);

        WebDriverListener webDriverListener = new DemoWebDriverListener();
        WebDriver decoratedAppiumDriver = new EventFiringDecorator<>(webDriverListener).decorate(originalAppiumDriver);
        PageFactory.initElements(new AppiumFieldDecorator(decoratedAppiumDriver), this);

        webElementNavOpenMenu1.click();

        webElementMenuItemLogIn1.click();

        webElementTxtUserName1.sendKeys("bob@example.com");

        webElementPwdPassword1.sendKeys("10203040");
    }
}

