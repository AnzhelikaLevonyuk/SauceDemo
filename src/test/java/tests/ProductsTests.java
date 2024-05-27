package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ProductsTests extends BaseTest {
    @Test(groups = "smoke",description = "Checking menu items: All displayed and Logout", dataProvider = "name for products")
    public void checkMenu(String productName) {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isMenuButtonDisplayed());
        productsPage.clickMenuButton();
        Assert.assertTrue(productsPage.isMenuDisplayed());

        productsPage.clickAddToCartButton(productName);
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.clickMenuButton();
        cartPage.clickAllItemLink();
        Assert.assertEquals(productsPage.getTitle(), "Products");

        cartPage.clickMenuButton();
        cartPage.clickLogOutLink();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }


    @Test(groups = "regression",description = "Checking name, description and price product on the Products page and checking the button 'Add to cart'", dataProvider = "data for products")
    public void addToCartProductTest(String productName, String productDescription, String productPrice) {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getProductPrice(productName), productPrice);
        Assert.assertEquals(productsPage.getProductDescription(productName), productDescription);
        Assert.assertTrue(productsPage.isItemImageDispalyed(productName));
        productsPage.clickAddToCartButton(productName);
        Assert.assertTrue(productsPage.isRemoveFromCartButtonDisplayed(productName));
    }


    @Test(groups = "smoke", description = "Checking price sorting: 'low to high' and 'high to low'")
    public void checkSortingPrice() {
        loginPage.login("standard_user", "secret_sauce");

        productsPage.applyFilter("Price (low to high)");
        String[] lowToHighPrice = {"$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99"};
        List<String> expectedItemsOrder = Arrays.asList(lowToHighPrice);
        Assert.assertEquals(productsPage.getProductPrices(), expectedItemsOrder);

        productsPage.applyFilter("Price (high to low)");
        expectedItemsOrder = List.of("$49.99", "$29.99", "$15.99", "$15.99", "$9.99", "$7.99");
        Assert.assertEquals(productsPage.getProductPrices(), expectedItemsOrder);
    }

    @Test(groups = "regression", description = "Open info page using product name and product image", dataProvider = "name for products")
    public void openInfoPage(String productName) {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickNameProduct(productName); //openInfoPageUsingName
        Assert.assertTrue(infoAboutProductPage.isBackButtonDisplayed());
        infoAboutProductPage.clickBackButton();
        productsPage.clickImageProduct(productName); //openInfoPageUsingImage
        Assert.assertTrue(infoAboutProductPage.isBackButtonDisplayed());
    }

}
