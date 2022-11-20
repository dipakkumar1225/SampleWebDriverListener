package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.pagefactory.*;
import io.appium.java_client.pagefactory.utils.WebDriverUnpackUtility;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DemoAppTest {

    @Test
    public void withOutPageFactory() {

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .autoGrantPermissions()
                .setFullReset(false)
                .setNoReset(true)
                .setApp(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk").toString())
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity("com.swaglabsmobileapp.SplashActivity");

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

        By locatorMenuList = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").descriptionStartsWith(\"menu item\").childSelector(new UiSelector().className(\"android.widget.TextView\"))");
        List<WebElement> webElementListMenuNames = decoratedAppiumDriver.findElements(locatorMenuList);
        ;
        System.out.println("Menu Name List : " + webElementListMenuNames.stream().map(WebElement::getText).collect(Collectors.toList()));

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


    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").descriptionStartsWith(\"test-\").childSelector(new UiSelector().className(\"android.widget.TextView\"))")
//    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc, \"menu item\")]/android.widget.TextView")
    private List<WebElement> webElementListMenuNames;

    @AndroidFindBy(accessibility = "test-WEBVIEW")
    private WebElement webElementMenuItemWebView1;


    @AndroidFindBy(accessibility = "test-enter a https url here...")
    private WebElement webElementTxtURL;

    @AndroidFindBy(accessibility = "test-GO TO SITE")
    private WebElement webElementBtnGoToSite;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.webkit.WebView\").text(\"Google\")")
    private WebElement webElementWebView;

    @FindBy(css = "input[aria-label=\"Search\"]")
    private WebElement webElementTxtInput;

    @AndroidFindBy(accessibility = "test-Username")
    private WebElement webElementTxtUserName1;
    @AndroidFindBy(accessibility = "test-Password")
    private WebElement webElementPwdPassword1;
    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement webElementBtnLogin1;

    @AndroidFindBy(accessibility = "test-Menu")
    private WebElement webElementNavOpenMenu1;

    @Test
    public void withPageFactory() {

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setPlatformName("android")
                .setDeviceName("ZD2222ZKDN")
                .autoGrantPermissions()
                .setFullReset(false)
                .setNoReset(true)
                .setAppWaitForLaunch(true)
                .setApp(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk").toString())
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity("com.swaglabsmobileapp.SplashActivity");

        URL url;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        AndroidDriver originalAppiumDriver = new AndroidDriver(url, uiAutomator2Options);

        WebDriverListener webDriverListener = new DemoWebDriverListener();
        WebDriver decoratedAppiumDriver = new EventFiringDecorator<>(webDriverListener).decorate(originalAppiumDriver);

        PageFactory.initElements(new AppiumFieldDecorator(decoratedAppiumDriver), this);

        String platform = null;
        String automation = null;
        if (decoratedAppiumDriver instanceof HasCapabilities) {
            Capabilities caps = ((HasCapabilities) decoratedAppiumDriver).getCapabilities();
            platform = CapabilityHelpers.getCapability(caps, "platformName", String.class);
            automation = CapabilityHelpers.getCapability(caps, "automationName", String.class);
        }
        PageFactory.initElements(new AppiumElementLocatorFactory(decoratedAppiumDriver, Duration.ofSeconds(1), new DefaultElementByBuilder(platform, automation)), this);

        //Login
        webElementTxtUserName1.sendKeys("standard_user");
        webElementPwdPassword1.sendKeys("secret_sauce");
        webElementBtnLogin1.click();

        //Nav-Menu
        webElementNavOpenMenu1.click();
        System.out.println("Menu Name List : " + webElementListMenuNames.stream().map(WebElement::getText).collect(Collectors.toList()));

        webElementMenuItemWebView1.click();

        webElementTxtURL.sendKeys("https://www.google.com");
        webElementBtnGoToSite.click();

        //Webview
        String strWebContextName = originalAppiumDriver.getContextHandles().stream().filter(ctx -> ctx.contains("WEBVIEW_")).findAny().orElse(null);
        if (Objects.nonNull(strWebContextName)) {
            System.out.println("WEB CONTEXT NAME " + strWebContextName);
            originalAppiumDriver.context(strWebContextName);
        }

        webElementTxtInput.sendKeys("Appium");
        decoratedAppiumDriver.quit();
    }


}

