package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void checkingPrice() {
        String productName = "Sauce Labs Bolt T-Shirt";

        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(productName);
        String priceOnProductsPage = productsPage.getProductPrice(productName);
        commonElementsOfAllPages.clickShoppingCartLink();
        Assert.assertEquals(cartPage.getProductPrice(productName), priceOnProductsPage);
    }

    @Test
    public void RemoveProductFromCart() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickAddToCartButton("Sauce Labs Bolt T-Shirt");
        productsPage.clickAddToCartButton("Sauce Labs Fleece Jacket");
        commonElementsOfAllPages.clickShoppingCartLink();
        cartPage.clickRemoveButton("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(cartPage.getCountProductsInTheCart(), 2);
    }

    @Test
    public void ContinueShoppingButton() {
        loginPage.login("standard_user", "secret_sauce");
        commonElementsOfAllPages.clickShoppingCartLink();
        cartPage.clickContinueShopping();
        Assert.assertEquals(commonElementsOfAllPages.getTitle(), "Products");
    }

    @Test
    public void openCheckoutPage() {
        loginPage.login("standard_user", "secret_sauce");
        commonElementsOfAllPages.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        Assert.assertEquals(commonElementsOfAllPages.getTitle(), "Checkout: Your Information");
    }

}
