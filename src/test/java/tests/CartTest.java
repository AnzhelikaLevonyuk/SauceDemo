package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {

    @BeforeMethod
    public void logIn() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Add to cart product and checking name, description and price ", dataProvider = "data for products")
    public void checkingPriceAndDescription(String productName, String productDescription, String productPrice) {

        productsPage.clickAddToCartButton(productName);

        productsPage.clickShoppingCartLink();
        Assert.assertEquals(cartPage.getTitle(), "Your Cart");

        Assert.assertEquals(cartPage.getProductPrice(productName), productPrice);
        Assert.assertEquals(cartPage.getProductDescription(productName), productDescription);
    }

    @Test(description = "Checking that the counter decreases after the product is removed from the cart", retryAnalyzer = Retry.class)
    public void removeProductFromCart() {

        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickAddToCartButton("Sauce Labs Bolt T-Shirt");
        productsPage.clickAddToCartButton("Sauce Labs Fleece Jacket");
        productsPage.clickShoppingCartLink();
        cartPage.clickRemoveButton("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(cartPage.getCountProductsInTheCart(), 2);
    }

    @Test(description = "Checking that 'Continue Shopping' and 'Checkout' buttons direct the user to the desired pages", dataProvider = "name for products")
    public void continueShoppingAndCheckoutButtons(String productName) {

        productsPage.clickAddToCartButton(productName);
        productsPage.clickShoppingCartLink();
        cartPage.clickContinueShopping();
        Assert.assertEquals(productsPage.getTitle(), "Products");

        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }

}
