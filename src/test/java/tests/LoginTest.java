package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "test data for negative login")
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"randomName", "randomPassword", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "wrongpassword", "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"}

        };
    }

    @Test(groups = "smoke",description = "Positive login test")
    public void positiveLoginTest() {
        boolean isShoppingCartDisplayed = loginPage.login("standard_user", "secret_sauce")
                .isShoppingCartDisplayed();
        Assert.assertTrue(isShoppingCartDisplayed);
    }

    @Test(groups = "regression",description = "Negative login test", dataProvider = "test data for negative login")
    public void negativeLoginTest(String email, String password, String expectedErrorMessage) {
        boolean isErrorMessageDisplayed = loginPage.login(email, password)
                .isErrorMessageDisplayed();
        Assert.assertTrue(isErrorMessageDisplayed);

        String errorMessageText = loginPage.getErrorMessageText();
        Assert.assertEquals(errorMessageText, expectedErrorMessage);
    }
}
