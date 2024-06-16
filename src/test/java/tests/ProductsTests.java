package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ProductsTests extends BaseTest {
    @Test(groups = {"smoke","userShouldBeLogin"},description = "Checking menu items: All displayed and Logout", dataProvider = "name for products")
    public void checkMenu(String productName) {

        boolean menuIsDisplay = productsPage.isMenuButtonDisplayed();
        Assert.assertTrue(menuIsDisplay);

       String title = productsPage.clickAddToCartButton(productName)
                .clickShoppingCartLink()
                .clickCheckoutButton()
                .clickMenuButton()
                .clickAllItemLink()
                .getTitle();
        Assert.assertEquals(title, "Products");

        boolean isLoginButtonDisplayed = cartPage.clickMenuButton()
                .clickLogOutLink()
                .isLoginButtonDisplayed();
        Assert.assertTrue(isLoginButtonDisplayed);
    }


    @Test(groups = {"regression","userShouldBeLogin"},description = "Checking name, description and price product on the Products page and checking the button 'Add to cart'", dataProvider = "data for products")
    public void addToCartProductTest(String productName, String productDescription, String productPrice) {

        Assert.assertEquals(productsPage.getProductPrice(productName), productPrice);
        Assert.assertEquals(productsPage.getProductDescription(productName), productDescription);
        Assert.assertTrue(productsPage.isItemImageDispalyed(productName));
        boolean isRemoveButtonDisplay = productsPage.clickAddToCartButton(productName)
                .isRemoveFromCartButtonDisplayed(productName);
        Assert.assertTrue(isRemoveButtonDisplay);
    }


    @Test(groups = {"smoke","userShouldBeLogin"}, description = "Checking price sorting: 'low to high' and 'high to low'")
    public void checkSortingPrice() {

        productsPage.applyFilter("Price (low to high)");
        List<String> expectedItemsOrder = List.of("$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99");
        Assert.assertEquals(productsPage.getProductPrices(), expectedItemsOrder);

        productsPage.applyFilter("Price (high to low)");
        expectedItemsOrder = List.of("$49.99", "$29.99", "$15.99", "$15.99", "$9.99", "$7.99");
        Assert.assertEquals(productsPage.getProductPrices(), expectedItemsOrder);
    }

    @Test(groups = {"regression","userShouldBeLogin"}, description = "Open info page using product name and product image", dataProvider = "name for products")
    public void openInfoPage(String productName) {

       boolean isBackButtonDisplay =  productsPage.clickNameProduct(productName) //openInfoPageUsingName
                .isBackButtonDisplayed();
        Assert.assertTrue(isBackButtonDisplay);

        isBackButtonDisplay = infoAboutProductPage.clickBackButton()
                .clickImageProduct(productName) //openInfoPageUsingImage
                .isBackButtonDisplayed();
        Assert.assertTrue(isBackButtonDisplay);
    }

}
