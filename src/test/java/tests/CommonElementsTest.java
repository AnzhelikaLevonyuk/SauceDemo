package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonElementsTest extends BaseTest {
    @Test
    public void checkMenu() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(commonElementsOfAllPages.isMenuButtonDisplayed());
        commonElementsOfAllPages.clickMenuButton();
        Assert.assertTrue(commonElementsOfAllPages.isMenuDisplayed());
    }

    @Test
    public void checkLogOut() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Onesie");
        commonElementsOfAllPages.clickShoppingCartLink();
        commonElementsOfAllPages.clickMenuButton();
        commonElementsOfAllPages.clickLogOutLink();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void checkAllItemsLink() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Onesie");
        commonElementsOfAllPages.clickShoppingCartLink();
        commonElementsOfAllPages.clickMenuButton();
        commonElementsOfAllPages.clickAllItemLink();
        Assert.assertEquals(commonElementsOfAllPages.getTitle(), "Products");
    }
}
