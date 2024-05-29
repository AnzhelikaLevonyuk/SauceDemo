package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InfoAboutProductPage extends BasePage {
    private static final By PRODUCT_NAME = By.className("inventory_details_name");
    private static final By PRODUCT_IMG = By.className("inventory_details_img");
    private static final By PRODUCT_DESCRIPTION = By.className("inventory_details_desc");
    private static final By PRODUCT_PRICE = By.className("inventory_details_price");
    private static final By ADD_TO_CART_BUTTON = By.id("add-to-cart");
    private static final By REMOVE_FROM_CART_BUTTON = By.id("remove");
    private static final By BACK_BUTTON = By.id("back-to-products");


    public InfoAboutProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBackButtonDisplayed() {
        return driver.findElement(BACK_BUTTON).isDisplayed();
    }

    @Step("Click 'Back' button on the 'Info about product' page")
    public void clickBackButton() {
        driver.findElement(BACK_BUTTON).click();
    }

    @Step("Get product description on the 'Info about product' page")
    public String getProductDescription() {
        return driver.findElement(PRODUCT_DESCRIPTION).getText();
    }

    @Step("Get product price on the 'Info about product' page")
    public String getProductPrice() {
        return driver.findElement(PRODUCT_PRICE).getText();
    }

    @Step("Get product description on the 'Info about product' page")
    public void clickAddToCartButton() {
        driver.findElement(ADD_TO_CART_BUTTON).click();
    }

    @Step("Click 'Remove' button on the 'Info about product' page")
    public void clickRemoveButton() {
        driver.findElement(REMOVE_FROM_CART_BUTTON).click();
    }
}
