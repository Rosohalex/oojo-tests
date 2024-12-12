package com.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.WebPage;

public class HomePage extends WebPage {
    
    private By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");
    private By cancelSubscriptionBtn = By.xpath("//*[@data-qa='subscribeModal']//a[.='No thanks']");

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void acceptCookiesAndCancelSubscription(){
        waitForElementVisibility(acceptCookiesBtn, 5);
        clickElement(acceptCookiesBtn);

        waitForElementVisibility(cancelSubscriptionBtn, 25);
        clickElement(cancelSubscriptionBtn);
    }
}
