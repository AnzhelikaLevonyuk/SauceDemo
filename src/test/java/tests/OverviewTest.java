package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

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
        BigDecimal totalProductsPrice = overviewPage.getProductPrice().stream().map(e -> e.getText().replace("$", "")).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal tax = BigDecimal.valueOf(Double.valueOf(overviewPage.getTax()));

        Assert.assertEquals(totalProductsPrice.toString(), overviewPage.getItemTotalSum());
        Assert.assertEquals(totalProductsPrice.add(tax).toString(), overviewPage.getTotalSum());

        overviewPage.clickCancelButton();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
}
