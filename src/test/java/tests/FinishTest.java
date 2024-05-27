package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class FinishTest extends BaseTest {
    @BeforeMethod
    public void logIn() {
        loginPage.login("standard_user", "secret_sauce");
    }
    @AfterMethod
    public void logOut() {
        productsPage.clickMenuButton();
        productsPage.clickLogOutLink();
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test(description = "End to end test for order", dataProvider = "name for products")
    public void clickBackHomeButton(String productName) {

        productsPage.clickAddToCartButton(productName);
        productsPage.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.setAllFields("Anzhelika", "Levonyuk", "123456");
        checkoutPage.clickContinueButton();
        overviewPage.clickFinishButton();
        Assert.assertTrue(finishPage.isBackHomeButtonDisplayed());
        Assert.assertTrue(finishPage.isTickIconDisplayed());
        Assert.assertTrue(finishPage.isCompleteHeaderDisplayed());
        Assert.assertTrue(finishPage.isCompleteTextDisplayed());
        finishPage.clickBackHomeButton();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
}
