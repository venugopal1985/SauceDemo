package com.saucedemo.example.stepdefs;

import com.saucedemo.example.Browser;
import com.saucedemo.example.DriverFactory;
import com.saucedemo.example.TestPlatform;
import com.saucedemo.example.pages.PagesFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Date;

public class StartingSteps extends DriverFactory implements En
{
    private RemoteWebDriver driver;
    private Date startDate, stopDate;

    public StartingSteps()
    {
        Before((Scenario scenario) -> {

            startDate = new Date();

                TestPlatform.Builder builder = new TestPlatform.Builder();
            // @formatter:off
            driver = DriverFactory.getDriverInstance(builder
                    .browser(Browser.EDGE)
                    .browserVersion("latest")
                    .platformName("Windows 11")
                    .build(), scenario);
            PagesFactory.start(driver);
                // @formatter:off
            driver = DriverFactory.getDriverInstance(builder
                    .browser(Browser.CHROME)
                    .browserVersion("latest")
                    .platformName("Windows 11")
                    .build(), scenario);
            PagesFactory.start(driver);
        });

        After((Scenario scenario) -> {
            boolean isSuccess = !scenario.isFailed();

            stopDate = new Date();
            System.out.println("Completed " + stopDate + ","+ (stopDate.getTime() - startDate.getTime()) / 1000L + " seconds.");

            if (driver == null)
            {
                return;
            }
            driver.quit();
        });

    }

    @Before("@Signup-DataDriven")
    public void signupSetup()
    {
        System.out.println("This should run everytime before any of the @Signup-DataDriven tagged scenario is going to run");
    }

    @After("@Signup-DataDriven")
    public void signupTeardown()
    {
        System.out.println("This should run everytime after any of the @Signup-DataDriven tagged scenario has run");
    }
}
