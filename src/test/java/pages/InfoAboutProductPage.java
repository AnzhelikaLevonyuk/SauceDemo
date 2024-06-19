package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InfoAboutProductPage extends BasePage {

    @FindBy(className = "inventory_details_desc")
    private WebElement productDescription;
    @FindBy(className = "inventory_details_price")
    private WebElement productPrice;
    @FindBy(id = "add-to-cart")
    private WebElement addToCartButton;
    @FindBy(id = "remove")
    private WebElement removeFromCartButton;
    @FindBy(id = "back-to-products")
    private WebElement backButton;


    public InfoAboutProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isBackButtonDisplayed() {
        return backButton.isDisplayed();
    }

    @Step("Click 'Back' button on the 'Info about product' page")
    public ProductsPage clickBackButton() {
        backButton.click();
        return new ProductsPage(driver);
    }

    @Step("Get product description on the 'Info about product' page")
    public String getProductDescription() {
        return productDescription.getText();
    }

    @Step("Get product price on the 'Info about product' page")
    public String getProductPrice() {
        return productPrice.getText();
    }

    @Step("Get product description on the 'Info about product' page")
    public InfoAboutProductPage clickAddToCartButton() {
        addToCartButton.click();
        return this;
    }

    @Step("Click 'Remove' button on the 'Info about product' page")
    public InfoAboutProductPage clickRemoveButton() {
        removeFromCartButton.click();
        return this;
    }
}
