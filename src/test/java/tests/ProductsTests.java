package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTests extends BaseTest {
    @Test
    public void addToCartProductTest() {
        String productName = "Sauce Labs Backpack";
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getProductPrice(productName), "$29.99");
        Assert.assertEquals(productsPage.getProductDescription(productName), "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        Assert.assertTrue(productsPage.isItemImageDispalyed(productName));
        productsPage.clickAddToCartButton(productName);
        Assert.assertTrue(productsPage.isRemoveFromCartButtonDisplayed(productName));
    }

    @Test
    public void checkDefaultSorting() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getDefaultSorting(), "Name (A to Z)");
    }

    @Test
    public void changeSorting() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.applyFilter("Price (low to high)"), "Price (low to high)");
    }

    @Test
    public void openInfoPageUsingName() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickNameProduct("Sauce Labs Fleece Jacket");
        Assert.assertTrue(infoAboutProductPage.isBackButtonDisplayed());
    }

    @Test
    public void openInfoPageUsingImage() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickImageProduct("Sauce Labs Fleece Jacket");
        Assert.assertTrue(infoAboutProductPage.isBackButtonDisplayed());
    }

    @Test
    public void openCartPage() {
        loginPage.login("standard_user", "secret_sauce");
        commonElementsOfAllPages.clickShoppingCartLink();
        Assert.assertEquals(commonElementsOfAllPages.getTitle(), "Your Cart");
    }
}
