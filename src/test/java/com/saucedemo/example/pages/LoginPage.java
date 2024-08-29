package com.saucedemo.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage
{
    public static final String PAGE_URL = "https://www.saucedemo.com";

    @FindBy(xpath = "//input[@data-test='username']")
    private WebElement usernameElem;

    @FindBy(xpath = "//input[@data-test='password']")
    private WebElement passwordElem;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginElem;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement()
    {
        return loginElem;
    }

    public void clickLogin()
    {
        loginElem.click();
    }

    public void enterPassword(String password)
    {
        passwordElem.click();
        passwordElem.sendKeys(password);
    }

    public void enterUsername(String username)
    {
        usernameElem.click();
        usernameElem.sendKeys(username);
    }

    public boolean hasLockedOutError()
    {
        WebElement elem = getDriver().findElement(By.xpath("//button[@class='error-button']"));
        return elem.isDisplayed();
    }

    public boolean hasUsernamePasswordError()
    {
        WebElement elem = getDriver().findElement(By.xpath("//button[@class='error-button']"));
        return elem.isDisplayed();
    }
}
