package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class CheckoutTest extends BaseTest {
    @DataProvider(name = "test data for negative checkout test")
    public Object[][] negativeCheckoutTestData() {
        return new Object[][]{
                {"", "", "", "Error: First Name is required"},
                {"Anzhelika", "", "12345", "Error: Last Name is required"},
                {"Anzhelika", "Levonyuk", "", "Error: Postal Code is required"},
                {"", "Levonyuk", "123", "Error: First Name is required"},
        };
    }

    @Test(groups = {"smoke","userShouldBeLogin"}, description = "Positive checkout test", dataProvider = "name for products")
    public void positiveCheckoutTest(String productName) {

        String title = productsPage.clickAddToCartButton(productName)
                .clickShoppingCartLink()
                .clickCheckoutButton()
                .setAllFields("Anzhelika", "Levonyuk", "123456")
                .clickContinueButton()
                .getTitle();

        Assert.assertEquals(title, "Checkout: Overview");
    }

    @Test(groups = {"regression","userShouldBeLogin"},description = "Negative checkout test", dataProvider = "test data for negative checkout test")
    public void negativeCheckoutTest(String firstName, String lastName, String zipCode, String errorMessage) {

         productsPage.clickAddToCartButton("Sauce Labs Backpack")
                .clickShoppingCartLink()
                .clickCheckoutButton()
                .setAllFields(firstName, lastName, zipCode)
                .clickContinueButton();

        boolean isErrorDisplay = checkoutPage.isErrorDisplay();
        Assert.assertTrue(isErrorDisplay);

        String error = checkoutPage.getErrorMessage();
        Assert.assertEquals(error, errorMessage);
    }

    @Test(groups = {"regression","userShouldBeLogin"}, description = "Checking that 'Cancel' button direct the user to the 'Your Cart' pages")
    public void cancelButton() {

        String title = productsPage.clickShoppingCartLink()
                .clickCheckoutButton()
                .clickCancelButton()
                .getTitle();
        Assert.assertEquals(title, "Your Cart");

    }
}
