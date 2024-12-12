package com.automation.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.pageobjects.OojoPage;

public class OojoPageTests {

    OojoPage oojoPage;

    @BeforeMethod
    public void setup() {
        oojoPage = new OojoPage();
    }
    
    @Test
    public void SearchFlightAndVerifyResultsPage() {
        oojoPage.homePage.acceptCookiesAndCancelSubscription();
        oojoPage.searchPage.searchOneWayOneTravelerFlightsInFuture("DFW", "NYC", 2);
        oojoPage.searchPage.verifyFlightsPricesAndDepartureDates();
    }

    @Test
    public void SearchFlightBookFirstVerifyCheckoutContactForm() {
        oojoPage.homePage.acceptCookiesAndCancelSubscription();
        oojoPage.searchPage.searchOneWayOneTravelerFlightsInFuture("DFW", "NYC", 2);
        
        String optionPrice = oojoPage.searchPage.getFlightOptionPriceAndSelectFirstFlight();
        oojoPage.flightDetails.verifyPrice(optionPrice);
        oojoPage.flightDetails.proceedWithBooking();

        oojoPage.checkout.enterEmail("asdsa");
        oojoPage.checkout.verifyEmailValidationMessage("Please enter a valid email address in format name@example.com");
        oojoPage.checkout.enterPhone("12345");
        oojoPage.checkout.verifyPhoneValidationMessage("Please enter a valid phone number in format +1 201 555 0123");

        oojoPage.checkout.clearEmailAndPhoneInputs();
        oojoPage.checkout.verifyEmailValidationMessage("Please enter a valid email address");
        oojoPage.checkout.verifyPhoneValidationMessage("Please enter a valid phone number in format +1 201 555 0123");
        
        oojoPage.checkout.enterEmail("adasd@asdsad.ls");
        oojoPage.checkout.verifyEmailValidationMessageElementNotPresent();
        oojoPage.checkout.enterPhone("201 555 0123");
        oojoPage.checkout.verifyPhoneValidationMessageElementNotPresent();
    }

    @AfterMethod
    public void tearDown() {
        oojoPage.CloseDriver();
    }
}
