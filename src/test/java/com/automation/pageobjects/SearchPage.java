package com.automation.pageobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.automation.WebPage;

public class SearchPage extends WebPage {
    
    private static final String PRICE_REGEX = "\\$\\d+\\.\\d{2}";

    private LocalDate searchDepartureDate;

    private By tripTypeBtn = By.cssSelector("[data-qa='tripTypeMenu']");
    private By tripPassengersBtn = By.cssSelector("[data-qa='tripPaxMenu']");
    private By progressBar = By.id("progress-bar-container");
    private By flight = By.cssSelector("[data-qa='pq-option']");
    private By flightPrice = By.cssSelector("[data-qa='pq-price']");
    private By flightDepartureDate = By.cssSelector("[data-qa='pqDateFrom']");

    public SearchPage(WebDriver driver){
        super(driver);
    }


    public void searchOneWayOneTravelerFlightsInFuture(String from, String to, int weeks){
        searchDepartureDate = LocalDate.now().plusWeeks(weeks);
        String formattedDate = getFormattedDateAsString(searchDepartureDate, "yyyy-MM-dd");
        String url = String.format("https://www.oojo.com/result/%s-%s/%s", from, to, formattedDate);
    
        navigateTo(url);
        waitForElementInvisibility(progressBar, 25);

        verifyDefaultSearchParameters();
    }

    private void verifyDefaultSearchParameters(){
        String actualTripType = getElementText(tripTypeBtn);
        String actualTripPassengers = getElementText(tripPassengersBtn);

        Assert.assertEquals(actualTripType, "One-way");
        Assert.assertEquals(actualTripPassengers, "1 Traveler");
    }

    public void verifyFlightsPricesAndDepartureDates(){
        List<WebElement> flights = getFlightOptions();

        for (WebElement flight : flights) {
            String actualPrice = flight.findElement(flightPrice).getText();
            Assert.assertTrue(actualPrice.matches(PRICE_REGEX));

            String actualDepartureDate = flight.findElement(flightDepartureDate).getText();
            String expectedDepartureDate = getFormattedDateAsString(searchDepartureDate,"EEE, MMM dd");
            Assert.assertEquals(actualDepartureDate, expectedDepartureDate);
        }
    }

    public String getFlightOptionPriceAndSelectFirstFlight(){
        List<WebElement> flights = getFlightOptions();
        WebElement firstFlight = flights.get(0);
        String optionPrice = firstFlight.findElement(flightPrice).getText();
        flights.get(0).click();

        return optionPrice;
    }

    private String getFormattedDateAsString(LocalDate date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        return date.format(formatter);
    }

    private List<WebElement> getFlightOptions(){
        return findElements(flight);
    }
}
