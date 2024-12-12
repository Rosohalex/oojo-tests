package com.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.WebPage;

public class Checkout extends WebPage {
    
    private By emailInput = By.cssSelector("[data-qa='email']");
    private By phoneInput = By.cssSelector("[data-qa='phoneNumber']");
    private By emailErrorLabel = By.cssSelector("[data-qa='email_err']");
    private By phoneErrorLabel = By.xpath("//*[@data-qa='phoneNumber']/../../../div[@data-qa='phoneNumberRepeat_err']");

    public Checkout(WebDriver driver){
        super(driver);
    }

    public void enterEmail(String input){
        scrollIntoView(emailInput);
        sendKeysToElement(emailInput, input);
    }

    public void enterPhone(String input){
        scrollIntoView(phoneInput);
        sendKeysToElement(phoneInput, input);
    }

    public void clearEmailAndPhoneInputs(){
        clearInput(emailInput);
        clearInput(phoneInput);
    }

    public void verifyEmailValidationMessage(String expectedMessage){
        String actualMessage = getElementText(emailErrorLabel);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    public void verifyEmailValidationMessageElementNotPresent(){
        boolean result = isElementPresent(emailErrorLabel);
        Assert.assertFalse(result);
    }

    public void verifyPhoneValidationMessageElementNotPresent(){
        boolean result = isElementPresent(phoneErrorLabel);
        Assert.assertFalse(result);
    }

    public void verifyPhoneValidationMessage(String expectedMessage){
        String actualMessage = getElementText(phoneErrorLabel);
        Assert.assertEquals(actualMessage, expectedMessage);
    }
}
