package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OverviewTest extends BaseTest {
    @Test(description = "Checking count of products, Item total, Total sum and that Cancel button redirected user to the Products page")
    public void checkOverviewPage() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        productsPage.clickAddToCartButton("Sauce Labs Fleece Jacket");
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.setAllFields("Anzhelika", "Levonyuk", "123456");
        checkoutPage.clickContinueButton();

        Assert.assertEquals(overviewPage.getCountProductsInTheOrder(), 2);
        Assert.assertEquals(overviewPage.calculateTotalProductsPrice().toString(), overviewPage.getItemTotalSum());
        Assert.assertEquals(overviewPage.calculateTotalPrice().toString(), overviewPage.getTotalSum());

        overviewPage.clickCancelButton();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
}
