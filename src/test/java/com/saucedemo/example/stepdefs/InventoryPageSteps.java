package com.saucedemo.example.stepdefs;

import com.saucedemo.example.pages.InventoryPage;
import com.saucedemo.example.pages.PagesFactory;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class InventoryPageSteps implements En
{
    public InventoryPageSteps()
    {
        And("^The user chooses a \"([^\"]*)\" by clicking 'Add To Cart'$", (String itemName) -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
            System.out.println("The user chooses a " + itemName+" by clicking 'Add To Cart' ");

            InventoryPage inventoryPage = pf.getInventoryPage();
            inventoryPage.addItemToCartByName(itemName);
        });

        And("^The user clicks on the shopping cart$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();
            System.out.println("The user clicks on the shopping cart");

            InventoryPage inventoryPage = pf.getInventoryPage();
            inventoryPage.clickOnShoppingCart();
        });

        And("^The user selects$", (DataTable dataTable) -> {
            PagesFactory pf = PagesFactory.getInstance();
            RemoteWebDriver driver = pf.getDriver();

            InventoryPage inventoryPage = pf.getInventoryPage();

            List<String> selectedItems = dataTable.asList(String.class);
            for (String itemName : selectedItems)
            {
                System.out.println("The user selects " + itemName);
                inventoryPage.addItemToCartByName(itemName);
            }
        });
    }
}
