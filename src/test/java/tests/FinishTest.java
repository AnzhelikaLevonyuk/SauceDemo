package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FinishTest extends BaseTest {
    @Test
    public void clickBackHomeButton() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton("Sauce Labs Backpack");
        commonElementsOfAllPages.clickShoppingCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage.setInputFirstName("Anzhelika");
        checkoutPage.setInputLastName("Levonyuk");
        checkoutPage.setInputZipCode("123456");
        checkoutPage.clickContinueButton();
        overviewPage.clickFinishButton();
        Assert.assertTrue(finishPage.isTickIconDisplayed());
        Assert.assertTrue(finishPage.isCompleteHeaderDisplayed());
        Assert.assertTrue(finishPage.isCompleteTextDisplayed());
        finishPage.clickBackHomeButton();
        Assert.assertEquals(commonElementsOfAllPages.getTitle(), "Products");
    }
}
