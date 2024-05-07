package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final static By INPUT_FIRST_NAME = By.id("first-name");
    private final static By INPUT_LAST_NAME = By.id("last-name");
    private final static By INPUT_ZIP_CODE = By.id("postal-code");
    private final static By CANCEL_BUTTON = By.id("cancel");
    private final static By CONTINUE_BUTTON = By.id("continue");
    private final static By ERROR_MESSAGE = By.className("error-message-container");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void setInputFirstName(String firstName) {
        driver.findElement(INPUT_FIRST_NAME).sendKeys(firstName);
    }

    public void setInputLastName(String lastName) {
        driver.findElement(INPUT_LAST_NAME).sendKeys(lastName);
    }

    public void setInputZipCode(String zipCode) {
        driver.findElement(INPUT_ZIP_CODE).sendKeys(zipCode);
    }

    public void clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public void clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public boolean isErrorDisplay() {
        return driver.findElement(ERROR_MESSAGE).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

}
