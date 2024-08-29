package com.saucedemo.example.stepdefs;

import com.saucedemo.example.pages.CheckOutStepOnePage;
import com.saucedemo.example.pages.CheckOutStepTwoPage;
import com.saucedemo.example.pages.PagesFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class CheckoutSteps
{
    @And("^The user provides the first name as \"([^\"]*)\" and last name as \"([^\"]*)\" and zip code as \"([^\"]*)\"$")
    public void theUserProvidesTheFirstNameAsAndLastNameAsAndZipCodeAs(String firstName, String lastName, String zipCode)
    throws Throwable
    {
        PagesFactory pf = PagesFactory.getInstance();
        RemoteWebDriver driver = pf.getDriver();
        System.out.println("The user provides the first name as " +firstName+" and last name as " +lastName+" and zip code as " +zipCode);

        CheckOutStepOnePage page = pf.getCheckOutStepOnePage();
        page.enterFirstName(firstName);
        page.enterLastName(lastName);
        page.enterPostalCode(zipCode);
    }

    @And("^The user clicks 'Continue'$")
    public void theUserClicksContinue()
    throws Throwable
    {
        PagesFactory pf = PagesFactory.getInstance();
        RemoteWebDriver driver = pf.getDriver();
        System.out.println( "The user clicks 'Continue'");

        CheckOutStepOnePage page = pf.getCheckOutStepOnePage();
        page.clickContinue();
    }

    @Then("^The item total should be \"([^\"]*)\"$")
    public void theItemTotalShouldBe(String itemTotal)
    throws Throwable
    {
        PagesFactory pf = PagesFactory.getInstance();
        RemoteWebDriver driver = pf.getDriver();
        System.out.println("The item total should be " + itemTotal);

        CheckOutStepTwoPage page = pf.getCheckOutStepTwoPage();
        page.waitForPageLoad();

        String expected = itemTotal;
        String actual = page.getItemTotal();
        Assert.assertEquals(actual, expected);
    }

    @And("^The tax should be \"([^\"]*)\"$")
    public void theTaxShouldBe(String tax)
    throws Throwable
    {
        PagesFactory pf = PagesFactory.getInstance();
        RemoteWebDriver driver = pf.getDriver();
        System.out.println( "The tax should be "+tax);

        CheckOutStepTwoPage page = pf.getCheckOutStepTwoPage();

        String expected = tax;
        String actual = page.getTax();
        Assert.assertEquals(actual, expected);
    }

    @And("^The total should be \"([^\"]*)\"$")
    public void theTotalShouldBe(String total)
    throws Throwable
    {
        PagesFactory pf = PagesFactory.getInstance();
        RemoteWebDriver driver = pf.getDriver();
        System.out.println( "The total should be "+ total);

        CheckOutStepTwoPage page = pf.getCheckOutStepTwoPage();

        String expected = total;
        String actual = page.getTotal();
        Assert.assertEquals(actual, expected);
    }
}
