package com.saucedemo.example.stepdefs;

import com.saucedemo.example.pages.InventoryPage;
import com.saucedemo.example.pages.LoginPage;
import com.saucedemo.example.pages.PagesFactory;
import cucumber.api.java8.En;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class LoginPageSteps implements En
{
    public LoginPageSteps()
    {
        Given("^The user is on the Home Page$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
            LoginPage loginPage = pf.getLoginPage();
            loginPage.navigateTo(LoginPage.PAGE_URL);
        });
        And("^The Page Load Time should be less than \"([^\"]*)\" msecs$", (String pageLoadTime) -> {
            // We need to give the capture routines time to capture the performance data before querying it
            // Thread.sleep(5000);
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
            System.out.println("The Page Load Time should be less than "+ pageLoadTime + " msec");

        });

        And("^The user provides the username as \"([^\"]*)\" and password as \"([^\"]*)\"$", (String username, String password) -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
            System.out.println( "The user provides the username as " +username + " and password as " +password);

            LoginPage loginPage = pf.getLoginPage();
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
           // Thread.sleep(2000);
        });

        And("^The user clicks the 'Login' button$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
            System.out.println( "The user clicks the 'Login' button");

            LoginPage loginPage = pf.getLoginPage();
            loginPage.clickLogin();
//            Thread.sleep(3000);
        });

        Then("^The user should login successfully and is brought to the inventory page$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
            System.out.println( "The user should login successfully and is brought to the inventory page");

            InventoryPage inventoryPage = pf.getInventoryPage();
            inventoryPage.waitForPageLoad();

            String currentUrl = PagesFactory.getInstance().getDriver().getCurrentUrl();
            Assert.assertEquals(currentUrl, InventoryPage.PAGE_URL);
        });

        Then("^The user should be shown a locked out message$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
            System.out.println( "The user should be shown a locked out message");

            LoginPage loginPage = pf.getLoginPage();
            Assert.assertTrue(loginPage.hasLockedOutError());
        });

        Then("^The user should be shown an invalid username/password message$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
          System.out.println("The user should be shown an invalid username/password message");

            LoginPage loginPage = pf.getLoginPage();
            Assert.assertTrue(loginPage.hasUsernamePasswordError());
        });
    }
}
