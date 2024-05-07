package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InfoAboutProductTest extends BaseTest {
    @Test
    public void backOnProductsPage() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickImageProduct("Sauce Labs Onesie");
        Assert.assertTrue(infoAboutProductPage.isBackButtonDisplayed());
        infoAboutProductPage.clickBackButton();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkInfo() {
        loginPage.login("standard_user", "secret_sauce");

        String productName = "Sauce Labs Bolt T-Shirt";
        String priceOnProductsPage = productsPage.getProductPrice(productName);
        String descriptionOnProductsPage = productsPage.getProductDescription(productName);

        productsPage.clickNameProduct(productName);

        Assert.assertEquals(infoAboutProductPage.getProductPrice(), priceOnProductsPage);
        Assert.assertEquals(infoAboutProductPage.getProductDescription(), descriptionOnProductsPage);
    }

    @Test
    public void addProductInCart() {
        loginPage.login("standard_user", "secret_sauce");

        productsPage.clickAddToCartButton("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickAddToCartButton("Sauce Labs Onesie");

        productsPage.clickImageProduct("Sauce Labs Bike Light");

        infoAboutProductPage.clickAddToCartButton();
        Assert.assertEquals(productsPage.getNumberOfItemsInTheCart(), "3");
    }

    @Test
    public void removeProductFromCart() {
        loginPage.login("standard_user", "secret_sauce");

        productsPage.clickAddToCartButton("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickAddToCartButton("Sauce Labs Onesie");

        productsPage.clickImageProduct("Test.allTheThings() T-Shirt (Red)");

        infoAboutProductPage.clickRemoveButton();
        Assert.assertEquals(productsPage.getNumberOfItemsInTheCart(), "1");
    }
}
