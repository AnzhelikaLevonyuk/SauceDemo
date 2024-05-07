package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OverviewTest extends BaseTest {
    @Test
    public void checkCountOfProductsInTheOrder() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickAddToCartButton("Sauce Labs Fleece Jacket");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.setInputFirstName("Anzhelika");
        checkoutPage.setInputLastName("Levonyuk");
        checkoutPage.setInputZipCode("123456");
        checkoutPage.clickContinueButton();
        Assert.assertEquals(overviewPage.getCountProductsInTheOrder(), 2);
    }

    @Test
    public void checkPriceTotal() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickAddToCartButton("Sauce Labs Fleece Jacket");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.setInputFirstName("Anzhelika");
        checkoutPage.setInputLastName("Levonyuk");
        checkoutPage.setInputZipCode("123456");
        checkoutPage.clickContinueButton();
        Assert.assertEquals(overviewPage.getItemTotal(), "Item total: $79.98");
        Assert.assertEquals(overviewPage.getTax(), "Tax: $6.40");
        Assert.assertEquals(overviewPage.getTotal(), "Total: $86.38");
    }

    @Test
    public void checkCancelButton() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.setInputFirstName("Anzhelika");
        checkoutPage.setInputLastName("Levonyuk");
        checkoutPage.setInputZipCode("123456");
        checkoutPage.clickContinueButton();
        overviewPage.clickCancelButton();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkFinishButton() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.setInputFirstName("Anzhelika");
        checkoutPage.setInputLastName("Levonyuk");
        checkoutPage.setInputZipCode("123456");
        checkoutPage.clickContinueButton();
        overviewPage.clickFinishButton();
        Assert.assertTrue(finishPage.isBackHomeButtonDisplayed());
    }
}
