package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    private final static String ITEM_CONTAINER = "//div[text()='%s']/ancestor::div[@class = 'inventory_item']";
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button[id^=add-to-cart]");
    private static final By REMOVE_FROM_CART_BUTTON = By.cssSelector("button[id^=remove]");
    private static final By ITEM_PRICE = By.className("inventory_item_price");
    private static final By ITEM_NAME = By.className("inventory_item_name");
    private static final By ITEM_DESCRIPTION = By.className("inventory_item_desc");
    private static final By ITEM_IMG = By.className("inventory_item_img");
    private static final By FILTER = By.className("product_sort_container");
    private static final By ACTIVE_OPTION = By.className("active_option");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get product name for this {productName} product on the Main page")
    private WebElement getProductCardByName(String productName) {
        return driver.findElement(By.xpath(String.format(ITEM_CONTAINER, productName)));
    }

    @Step("Get product description for this {productName} product on the Main page")
    public String getProductDescription(String productName) {
        return this.getProductCardByName(productName).findElement(ITEM_DESCRIPTION).getText();
    }

    @Step("Get product price for this {productName} product on the Main page")
    public String getProductPrice(String productName) {
        return this.getProductCardByName(productName).findElement(ITEM_PRICE).getText();
    }

    @Step("Click 'Add to cart' button on the Main page")
    public ProductsPage clickAddToCartButton(String productName) {
        this.getProductCardByName(productName).findElement(ADD_TO_CART_BUTTON).click();
        return this;
    }

    public boolean isRemoveFromCartButtonDisplayed(String productName) {
        return this.getProductCardByName(productName).findElement(REMOVE_FROM_CART_BUTTON).isDisplayed();
    }

    @Step("Click 'Remove' button on the Main page")
    public ProductsPage clickRemoveFromCartButton(String productName) {
        this.getProductCardByName(productName).findElement(REMOVE_FROM_CART_BUTTON).click();
        return this;
    }

    public boolean isItemImageDispalyed(String productName) {
        return getProductCardByName(productName).findElement(ITEM_IMG).isDisplayed();
    }

    public String getDefaultSorting() {
        Select select = new Select(driver.findElement(FILTER));
        return select.getFirstSelectedOption().getText();
    }

    @Step("Apply {optionText} filter")
    public String applyFilter(String optionText) {
        Select select = new Select(driver.findElement(FILTER));
        select.selectByVisibleText(optionText);
        return driver.findElement(ACTIVE_OPTION).getText();
    }

    @Step("Get list with all products")
    public List<String> getProductPrices() {
        List<WebElement> actualItems = driver.findElements(ITEM_PRICE);
        return actualItems.stream()
                .map(WebElement::getText)
                .toList();
    }

   @Step("Click product name {productName} to open 'Info' page")
    public InfoAboutProductPage clickNameProduct(String productName) {
        this.getProductCardByName(productName).findElement(ITEM_NAME).click();
        return new InfoAboutProductPage(driver);
    }

    @Step("Click on the image in {productName} to open 'Info' page")
    public InfoAboutProductPage clickImageProduct(String productName) {
        this.getProductCardByName(productName).findElement(ITEM_IMG).click();
        return new InfoAboutProductPage(driver);
    }
}
