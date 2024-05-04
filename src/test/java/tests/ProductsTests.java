package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ProductsTests extends BaseTest {
    @Test
    public void checkMenu() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isMenuButtonDisplayed());
        productsPage.clickMenuButton();
        Assert.assertTrue(productsPage.isMenuDisplayed());
    }

    @Test
    public void checkAllItemsLink() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Onesie");
        productsPage.clickShoppingCartLink();
        cartPage.clickMenuButton();
        cartPage.clickAllItemLink();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkLogOut() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Onesie");
        productsPage.clickShoppingCartLink();
        cartPage.clickMenuButton();
        cartPage.clickLogOutLink();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

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
    public void changeSortingAndVerifiedSortingLabel() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.applyFilter("Price (low to high)"), "Price (low to high)");
    }

    @Test
    public void changeSorting_priceLowToHigh() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.applyFilter("Price (low to high)");
        String[] lowToHighPrice = {"$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99"};
        List<String> expectedItemsOrder = Arrays.asList(lowToHighPrice);
        Assert.assertEquals(productsPage.getPrice(), expectedItemsOrder);
    }

    @Test
    public void changeSorting_priceHighToLow() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.applyFilter("Price (high to low)");
        String[] lowToHighPrice = {"$49.99", "$29.99", "$15.99", "$15.99", "$9.99", "$7.99"};
        List<String> expectedItemsOrder = Arrays.asList(lowToHighPrice);
        Assert.assertEquals(productsPage.getPrice(), expectedItemsOrder);
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
        productsPage.clickShoppingCartLink();
        Assert.assertEquals(cartPage.getTitle(), "Your Cart");
    }
}
