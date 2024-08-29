package com.saucedemo.example.stepdefs;

import com.saucedemo.example.pages.CartPage;
import com.saucedemo.example.pages.PagesFactory;
import cucumber.api.java.en.And;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartSteps implements En
{
    @And("^The user clicks 'Checkout'$")
    public void theUserClicksCheckout()
    throws Throwable
    {
        CartPage cartPage = PagesFactory.getInstance().getCartPage();
        cartPage.clickCheckout();
    }

    public CartSteps()
    {
        Then("^There should be \"([^\"]*)\" items in the shopping cart$", (String count) -> {
            PagesFactory pf = PagesFactory.getInstance();
            WebDriver driver = pf.getDriver();
            System.out.println("There should be " + count + " items in the shopping cart");

            CartPage cartPage = pf.getCartPage();
            int actualCount = cartPage.getItemCount();
            int expectedCount = Integer.parseInt(count);
            Assert.assertEquals(actualCount, expectedCount);
        });
    }
}
