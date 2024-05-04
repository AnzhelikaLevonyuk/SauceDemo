package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    @Test
    public void positiveCheckoutTest() {

        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();

        checkoutPage.setInputFirstName("Anzhelika");
        checkoutPage.setInputLastName("Levonyuk");
        checkoutPage.setInputZipCode("123456");
        checkoutPage.clickContinueButton();

        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test
    public void negativeCheckoutTest() {

        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();

        checkoutPage.setInputFirstName("");
        checkoutPage.setInputLastName("");
        checkoutPage.setInputZipCode("");
        checkoutPage.clickContinueButton();

        Assert.assertTrue(checkoutPage.isErrorDisplay());
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test
    public void cancelButton() {

        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Bike Light");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.clickCancelButton();
        Assert.assertEquals(cartPage.getTitle(), "Your Cart");
    }
}
