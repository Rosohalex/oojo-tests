package com.automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebPage {

    private WebDriver driver;

    protected WebPage(){}

    protected WebPage(WebDriver driver){
        this.driver = driver;
    }

    protected WebDriver initializeDriver(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        return driver;
    }

    protected void closeDriver() {
        if (driver != null) {
            driver.quit();
        } else {
            throw new IllegalStateException("Driver is not initialized");
        }
    }

    protected void navigateTo(String url) {
        if (driver != null) {
            driver.get(url);
        } else {
            throw new IllegalStateException("Driver is not initialized");
        }
    }

    protected List<WebElement> findElements(By locator){
        return  driver.findElements(locator);
    }

    protected void clickElement(By locator) {
        waitForElementToBeClickable(locator, 5);
        WebElement element = findElement(locator);
        element.click();
    }

    protected String getElementText(By locator){
        return driver.findElement(locator).getText();
    }

    protected void sendKeysToElement(By locator, String text) {
        waitForElementVisibility(locator, 10);
        WebElement element = findElement(locator);
        element.sendKeys(text);
        element.sendKeys(Keys.TAB);
    }

    protected void clearInput(By locator){
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(Keys.TAB);
    }

    protected void scrollIntoView(By locator) {
        waitForElementVisibility(locator, 10);
        WebElement element = findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected boolean isElementPresent(By locator){
        try {
            findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private WebElement findElement(By locator){
        return  driver.findElement(locator);
    }

    protected void waitForElementVisibility(By locator, int timeoutInSeconds){
        WebDriverWait wait = getWebDriverWait(timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementInvisibility(By locator, int timeoutInSeconds){
        WebDriverWait wait = getWebDriverWait(timeoutInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private void waitForElementToBeClickable(By locator, int timeoutInSeconds){
        WebDriverWait wait = getWebDriverWait(timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebDriverWait getWebDriverWait(int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait;
    }
}
