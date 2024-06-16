package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class OverviewTest extends BaseTest {
    @Test(groups = {"smoke","userShouldBeLogin"}, description = "Checking count of products, Item total, Total sum and that Cancel button redirected user to the Products page")
    public void checkOverviewPage() {

       int countProductsInTheOrder =  productsPage.clickAddToCartButton("Sauce Labs Backpack")
                .clickAddToCartButton("Sauce Labs Fleece Jacket")
                .clickShoppingCartLink()
                .clickCheckoutButton()
                .setAllFields("Anzhelika", "Levonyuk", "123456")
                .clickContinueButton()
                .getCountProductsInTheOrder();

        Assert.assertEquals(countProductsInTheOrder, 2);

        BigDecimal totalProductsPrice = overviewPage.getProductPrice().stream().map(e -> e.getText().replace("$", "")).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal tax = BigDecimal.valueOf(Double.valueOf(overviewPage.getTax()));

        Assert.assertEquals(totalProductsPrice.toString(), overviewPage.getItemTotalSum());
        Assert.assertEquals(totalProductsPrice.add(tax).toString(), overviewPage.getTotalSum());

        String title = overviewPage.clickCancelButton()
                        .getTitle();
        Assert.assertEquals(title, "Products");
    }
}
