package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {
    private final static String ITEM_CONTAINER = "//div[text()='%s']/ancestor::div[@class = 'cart_item_label']";
    private final static By PRODUCT_IN_THE_CART = By.className("cart_item");
    private static final By ITEM_PRICE = By.className("inventory_item_price");
    private static final By ITEM_DESCRIPTION = By.className("inventory_item_desc");
    private static final By REMOVE_BUTTON = By.cssSelector("button[id^=remove-sauce-labs]");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getProductCardByName(String productName) {
        return driver.findElement(By.xpath(String.format(ITEM_CONTAINER, productName)));
    }

    public String getProductPrice(String productName) {
        return this.getProductCardByName(productName).findElement(ITEM_PRICE).getText();
    }

    public String getProductDescription(String productName) {
        return this.getProductCardByName(productName).findElement(ITEM_DESCRIPTION).getText();
    }

    public void clickRemoveButton(String productName) {
        this.getProductCardByName(productName).findElement(REMOVE_BUTTON).click();
    }

    public void clickContinueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public Integer getCountProductsInTheCart() {
        return driver.findElements(PRODUCT_IN_THE_CART).size();
    }
}
