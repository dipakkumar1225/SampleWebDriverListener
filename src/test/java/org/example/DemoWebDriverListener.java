package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class DemoWebDriverListener implements WebDriverListener {

    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println(" Before Find Element " + locator);
    }

    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println(" After Find Element " + locator);
    }
}
