package com.saucedemo.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractPage
{
    public static final String PAGE_URL = "https://www.saucedemo.com/cart.html";

    @FindBy(xpath = "//button[text()='Open Menu']")
    private WebElement hamburgerElem;

    @FindBy(xpath = "//*[@id=\"checkout\"]")
    private WebElement checkoutElem;

    @FindAll({@FindBy(css = "#cart_contents_container > div > div.cart_list > div.cart_item")})
    private List<WebElement> itemsList;

    public CartPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement()
    {
        return hamburgerElem;
    }

    public void clickCheckout()
    {
        checkoutElem.click();
    }

    public int getItemCount()
    {
        return itemsList.size();
    }

}
