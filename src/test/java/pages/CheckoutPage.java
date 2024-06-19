package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement inputFirstName;

    @FindBy(id = "last-name")
    private WebElement inputLastName;

    @FindBy(id = "postal-code")
    private WebElement inputZipCode;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;


    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @Step("Fill user info: {firstName}, {lastName}, {zipCode}")
    public CheckoutPage setAllFields(String firstName, String lastName, String zipCode) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputZipCode.sendKeys(zipCode);
        return this;
    }

    @Step("Click 'Cancel' button on the 'Checkout' page")
    public CartPage clickCancelButton() {
        cancelButton.click();
        return new CartPage(driver);
    }

    @Step("Click 'Continue' button on the 'Checkout' page")
    public OverviewPage clickContinueButton() {
        continueButton.click();
        return new OverviewPage(driver);
    }

    public boolean isErrorDisplay() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}
