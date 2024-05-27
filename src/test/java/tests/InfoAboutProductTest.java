package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InfoAboutProductTest extends BaseTest {
    @Test(description = "Checking that 'Back to products' buttons direct the user to the 'Products' pages", dataProvider = "name for products")
    public void backOnProductsPage(String productName) {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickImageProduct(productName);
        Assert.assertTrue(infoAboutProductPage.isBackButtonDisplayed());
        infoAboutProductPage.clickBackButton();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }


    @Test(description = "Checking name, description and price product on the Info page ", dataProvider = "data for products")
    public void checkInfo(String productName, String description, String price) {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickNameProduct(productName);
        Assert.assertEquals(infoAboutProductPage.getProductPrice(), price);
        Assert.assertEquals(infoAboutProductPage.getProductDescription(), description);
    }

    @Test(description = "Checking that the counter involve and decress after the product is added/remove to the cart from the Info page")
    public void addProductInCart() {
        loginPage.login("standard_user", "secret_sauce");

        productsPage.clickAddToCartButton("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickAddToCartButton("Sauce Labs Onesie");
        productsPage.clickImageProduct("Sauce Labs Bike Light");
        infoAboutProductPage.clickAddToCartButton();
        Assert.assertEquals(productsPage.getNumberOfItemsInTheCart(), "3");

        infoAboutProductPage.clickRemoveButton();
        Assert.assertEquals(productsPage.getNumberOfItemsInTheCart(), "2");
    }
}
