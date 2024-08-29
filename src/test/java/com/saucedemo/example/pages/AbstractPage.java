package com.saucedemo.example.pages;

import com.saucedemo.example.MyFluentWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.time.temporal.ChronoUnit;

abstract class AbstractPage
{
    private WebDriver driver;
    protected Wait<WebDriver> wait;

    AbstractPage(WebDriver driver)
    {
        this.driver = driver;

        wait = new MyFluentWait<WebDriver>(driver)
                .withTimeout(60, ChronoUnit.SECONDS)
                .pollingEvery(2, ChronoUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public abstract WebElement getPageLoadedTestElement();

    protected WebDriver getDriver()
    {
        return driver;
    }

    public void waitForPageLoad()
    {
        WebElement testElement = getPageLoadedTestElement();

        // Wait for the page to load...
        wait.until(ExpectedConditions.visibilityOf(testElement));
    }

    public void navigateTo(String url)
    {
        WebDriver driver = getDriver();

        try
        {
            driver.navigate().to(url);
        }
        catch (java.lang.Exception e)
        {
            if (e instanceof TimeoutException)
            {
                System.out.println("Timeout loading home page");
            }
            else if (e instanceof ScriptTimeoutException)
            {
                System.out.println("Script Timeout loading home page");
            }
            else
            {
                System.err.println(e.getMessage());
            }
        }
    }


}
