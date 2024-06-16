package tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {


    @Test(groups= {"userShouldBeLogin", "regression"}, description = "Add to cart product and checking name, description and price ", dataProvider = "data for products")
    public void checkingPriceAndDescription(String productName, String productDescription, String productPrice) {

        String titlePage = productsPage.clickAddToCartButton(productName)
                .clickShoppingCartLink()
                .getTitle();
        Assert.assertEquals(titlePage, "Your Cart");

        String price = cartPage.getProductPrice(productName);
        Assert.assertEquals(price, productPrice);

        String description = cartPage.getProductDescription(productName);
        Assert.assertEquals(description, productDescription);
    }

    @Test(groups= {"userShouldBeLogin", "smoke"}, description = "Checking that the counter decreases after the product is removed from the cart", retryAnalyzer = Retry.class)
    public void removeProductFromCart() {

        int countProductsInTheCart = productsPage.clickAddToCartButton("Sauce Labs Backpack")
                .clickAddToCartButton("Sauce Labs Bolt T-Shirt")
                .clickAddToCartButton("Sauce Labs Fleece Jacket")
                .clickShoppingCartLink()
                .clickRemoveButton("Sauce Labs Bolt T-Shirt")
                .getCountProductsInTheCart();
        Assert.assertEquals(countProductsInTheCart, 2);
    }

    @Test(groups= {"userShouldBeLogin", "regression"}, description = "Checking that 'Continue Shopping' and 'Checkout' buttons direct the user to the desired pages", dataProvider = "name for products")
    public void continueShoppingAndCheckoutButtons(String productName) {

       String title =  productsPage.clickAddToCartButton(productName)
                .clickShoppingCartLink()
                .clickContinueShopping()
                .getTitle();
        Assert.assertEquals(title, "Products");

       title = productsPage.clickShoppingCartLink()
                .clickCheckoutButton()
                .getTitle();
        Assert.assertEquals(title, "Checkout: Your Information");
    }

}
