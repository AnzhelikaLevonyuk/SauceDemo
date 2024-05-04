package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void checkShopingCart() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isMenuButtonDisplayed());
    }
    @Test
    public void checkingPrice() {
        String productName = "Sauce Labs Bolt T-Shirt";

        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(productName);
        String priceOnProductsPage = productsPage.getProductPrice(productName);
        productsPage.clickShoppingCartLink();
        Assert.assertEquals(cartPage.getProductPrice(productName), priceOnProductsPage);
    }

    @Test
    public void removeProductFromCart() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickAddToCartButton("Sauce Labs Bolt T-Shirt");
        productsPage.clickAddToCartButton("Sauce Labs Fleece Jacket");
        productsPage.clickShoppingCartLink();
        cartPage.clickRemoveButton("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(cartPage.getCountProductsInTheCart(), 2);
    }

    @Test
    public void continueShoppingButton() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickShoppingCartLink();
        cartPage.clickContinueShopping();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void openCheckoutPage() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }

}
