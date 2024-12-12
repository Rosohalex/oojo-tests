package com.automation.pageobjects;

import org.openqa.selenium.WebDriver;

import com.automation.WebPage;

public class OojoPage extends WebPage {
    
    private static final String URL = "https://www.oojo.com";

    WebDriver driver;

    public SearchPage searchPage;
    public HomePage homePage;
    public FlightDetails flightDetails;
    public Checkout checkout;
    
    public OojoPage() {
        driver = initializeDriver();
        navigateTo(URL);
        searchPage = new SearchPage(driver);
        homePage = new HomePage(driver);
        flightDetails = new FlightDetails(driver);
        checkout = new Checkout(driver);
    }

    public void CloseDriver(){
        closeDriver();
    }
}
