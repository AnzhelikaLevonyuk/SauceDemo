package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class FinishTest extends BaseTest {

    @AfterMethod
    public void logOut() {
        boolean isLoginButtonDisplayed = productsPage.clickMenuButton()
                .clickLogOutLink()
                .isLoginButtonDisplayed();
        Assert.assertTrue(isLoginButtonDisplayed);
    }

    @Test(groups = {"userShouldBeLogin", "smoke"}, description = "End to end test for order", dataProvider = "name for products")
    public void clickBackHomeButton(String productName) {

        boolean isBackButtonDisplayed = productsPage.clickAddToCartButton(productName)
                .clickShoppingCartLink()
                .clickCheckoutButton()
                .setAllFields("Anzhelika", "Levonyuk", "123456")
                .clickContinueButton()
                .clickFinishButton()
                .isBackHomeButtonDisplayed();

        Assert.assertTrue(isBackButtonDisplayed);
        Assert.assertTrue(finishPage.isTickIconDisplayed());
        Assert.assertTrue(finishPage.isCompleteHeaderDisplayed());
        Assert.assertTrue(finishPage.isCompleteTextDisplayed());

        String title = finishPage.clickBackHomeButton()
                        .getTitle();
        Assert.assertEquals(title, "Products");
    }
}
