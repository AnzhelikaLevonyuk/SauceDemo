package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    private static final By TITLE_PRODUCTS_PAGE = By.className("title");
    private static final By SHOPPING_CART = By.className("shopping_cart_link");
    private static final By NUMBER_OF_ITEMS_IN_THE_CART = By.className("shopping_cart_badge");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By ALL_ITEM_LINK = By.id("inventory_sidebar_link");
    private static final By ABOUT_LINK = By.id("about_sidebar_link");
    private static final By LOG_OUT_LINK = By.id("logout_sidebar_link");
    private static final By RESET_APP_STATE = By.id("reset_sidebar_link");
    private static final By X_BUTTON = By.id("react-burger-cross-btn");
    private static final By MENU = By.className("bm-menu");
    private static final By FOOTER = By.className("footer");


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(TITLE_PRODUCTS_PAGE).getText();
    }

    public boolean isShoppingCartDisplayed() {
        return driver.findElement(SHOPPING_CART).isDisplayed();
    }

    public void clickShoppingCartLink() {
        driver.findElement(SHOPPING_CART).click();
    }

    public String getNumberOfItemsInTheCart() {
        return driver.findElement(NUMBER_OF_ITEMS_IN_THE_CART).getText();
    }

    public boolean isMenuButtonDisplayed() {
        return driver.findElement(MENU_BUTTON).isDisplayed();
    }

    public boolean isMenuDisplayed() {
        return driver.findElement(MENU).isDisplayed();
    }

    public boolean isFooterDisplayed() {
        return driver.findElement(FOOTER).isDisplayed();
    }

    public void clickMenuButton() {
        driver.findElement(MENU_BUTTON).click();
    }

    public void clickAllItemLink() {
        driver.findElement(ALL_ITEM_LINK).click();
    }

    public void clickLogOutLink() {
        driver.findElement(LOG_OUT_LINK).click();
    }

    public void clickAboutLink() {
        driver.findElement(ABOUT_LINK).click();
    }

    public void clickResetAppStateLink() {
        driver.findElement(RESET_APP_STATE).click();
    }

    public void clickCloseMenuButton() {
        driver.findElement(X_BUTTON).click();
    }
}
