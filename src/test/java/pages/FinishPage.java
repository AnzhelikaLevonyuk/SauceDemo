package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinishPage extends BasePage {

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;
    @FindBy(className = "pony_express")
    private WebElement tickIcon;
    @FindBy(className = "complete-header")
    private WebElement completeHeader;
    @FindBy(className = "complete-text")
    private WebElement completeText;

    public FinishPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isTickIconDisplayed() {
        return tickIcon.isDisplayed();
    }

    public boolean isCompleteHeaderDisplayed() {
        return completeHeader.isDisplayed();
    }

    public boolean isCompleteTextDisplayed() {
        return completeText.isDisplayed();
    }

    public boolean isBackHomeButtonDisplayed() {
        return backHomeButton.isDisplayed();
    }

    @Step("Click 'Back home' button on the last page")
    public ProductsPage clickBackHomeButton() {
        backHomeButton.click();
        return new ProductsPage(driver);
    }
}
