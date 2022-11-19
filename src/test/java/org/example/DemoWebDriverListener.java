package org.example;

import io.appium.java_client.functions.ExpectedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class DemoWebDriverListener implements WebDriverListener {

    public void beforeFindElement(WebDriver driver, By locator) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
        System.out.println(" Before Find Element ");
    }

    public void beforeFindElements(WebDriver driver, By locator) {
        System.out.println(" Before Find Elements " + locator);
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
    }
//
//    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
//        System.out.println(" After Find Element " + locator);
//    }
//
//    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
//        System.out.println(" After Find Elements " + result);
//    }
//
//    public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
//        System.out.println("Before any webdriver call 1 " + driver);
//        System.out.println("Before any webdriver call 2 " + method);
//        System.out.println("Before any webdriver call 3 " + Arrays.toString(args));
//    }

    public void beforeAnyCall(Object target, Method method, Object[] args) {
        System.out.println("Before any beforeAnyCall call 1 " + target);
        System.out.println("Before any beforeAnyCall call 2 " + method);
        System.out.println("Before any beforeAnyCall call 3 " + Arrays.toString(args));
    }

}
