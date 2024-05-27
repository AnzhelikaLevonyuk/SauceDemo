package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class CheckoutTest extends BaseTest {
    @DataProvider(name = "test data for negative checkout test")
    public Object[][] negativeCheckoutTestData() {
        return new Object[][]{
                {"", "", "", "Error: First Name is required"},
                {"Anzhelika", "", "12345", "Error: Last Name is required"},
                {"Anzhelika", "Levonyuk", "", "Error: Postal Code is required"},
                {"", "Levonyuk", "123", "Error: First Name is required"},
        };
    }

    @Test(description = "Positive checkout test", dataProvider = "name for products")
    public void positiveCheckoutTest(String productName) {

        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(productName);
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.setAllFields("Anzhelika", "Levonyuk", "123456");
        checkoutPage.clickContinueButton();

        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test(description = "Negative checkout test", dataProvider = "test data for negative checkout test")
    // как использовать 2 dataProvider в одном методе?
    public void negativeCheckoutTest(String firstName, String lastName, String zipCode, String errorMessage) {

        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();

        checkoutPage.setAllFields(firstName, lastName, zipCode);
        checkoutPage.clickContinueButton();

        Assert.assertTrue(checkoutPage.isErrorDisplay());
        Assert.assertEquals(checkoutPage.getErrorMessage(), errorMessage);
    }

    @Test(description = "Checking that 'Cancel' button direct the user to the 'Your Cart' pages")
    public void cancelButton() {

        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.clickCancelButton();
        Assert.assertEquals(cartPage.getTitle(), "Your Cart");

    }
}
