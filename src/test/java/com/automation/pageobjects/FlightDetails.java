package com.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.WebPage;

public class FlightDetails extends WebPage {
    
    private By detailsPriceLabel = By.cssSelector("[data-qa='pqDetailsPrice']");
    private By bookBtn = By.cssSelector("[data-qa='bookFlightBtn']");

    public FlightDetails(WebDriver driver){
        super(driver);
    }

    public void verifyPrice(String expectedPrice){
        String actualDetailsPrice = getElementText(detailsPriceLabel);
        Assert.assertEquals(actualDetailsPrice, expectedPrice);
    }

    public void proceedWithBooking(){
        clickElement(bookBtn);
    }
}
