package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InfoAboutProductTest extends BaseTest {
    @Test(groups = {"regression", "userShouldBeLogin"}, description = "Checking that 'Back to products' buttons direct the user to the 'Products' pages", dataProvider = "name for products")
    public void backOnProductsPage(String productName) {

        boolean isBackButtonDisplay = productsPage.clickImageProduct(productName)
                .isBackButtonDisplayed();
        Assert.assertTrue(isBackButtonDisplay);

        String title = infoAboutProductPage.clickBackButton()
                .getTitle();
        Assert.assertEquals(title, "Products");
    }


    @Test(groups = {"regression", "userShouldBeLogin"}, description = "Checking name, description and price product on the Info page ", dataProvider = "data for products")
    public void checkInfo(String productName, String description, String price) {
        String productPrice = productsPage.clickNameProduct(productName)
                .getProductPrice();
        Assert.assertEquals(productPrice, price);
        String productDescription = infoAboutProductPage.getProductDescription();
        Assert.assertEquals(productDescription, description);
    }

    @Test(groups = {"smoke", "userShouldBeLogin"}, description = "Checking that the counter involve and decress after the product is added/remove to the cart from the Info page")
    public void addProductInCart() {

        String numberOfItemInTheCart = productsPage.clickAddToCartButton("Test.allTheThings() T-Shirt (Red)")
                .clickAddToCartButton("Sauce Labs Onesie")
                .clickImageProduct("Sauce Labs Bike Light")
                .clickAddToCartButton()
                .getNumberOfItemsInTheCart();

        Assert.assertEquals(numberOfItemInTheCart, "3");

       numberOfItemInTheCart = infoAboutProductPage.clickRemoveButton()
                .getNumberOfItemsInTheCart();
        Assert.assertEquals(numberOfItemInTheCart, "2");
    }
}
