package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;

    @FindBy(className = "title")
    private WebElement titleProductsPage;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCart;
    @FindBy(className = "shopping_cart_badge")
    private WebElement numberOfItemInTheCart;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;
    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemLink;
    @FindBy(id = "about_sidebar_link")
    private WebElement aboutLink;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logOutLink;
    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppState;
    @FindBy(id = "react-burger-cross-btn")
    private WebElement xButton;
    @FindBy(className = "bm-menu")
    private WebElement menu;
    @FindBy(className = "footer")
    private WebElement footer;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return titleProductsPage.getText();
    }

    public boolean isShoppingCartDisplayed() {
        return shoppingCart.isDisplayed();
    }

    public CartPage clickShoppingCartLink() {
        shoppingCart.click();
        return new CartPage(driver);
    }

    public String getNumberOfItemsInTheCart() {
        return numberOfItemInTheCart.getText();
    }

    public boolean isMenuButtonDisplayed() {
        return menuButton.isDisplayed();
    }

    public boolean isMenuDisplayed() {
        return menu.isDisplayed();
    }

    public boolean isFooterDisplayed() {
        return footer.isDisplayed();
    }

    public BasePage clickMenuButton() {
        menuButton.click();
        return this;
    }

    public BasePage clickAllItemLink() {
        allItemLink.click();
        return this;
    }

    public LoginPage clickLogOutLink() {
        logOutLink.click();
        return new LoginPage(driver);
    }

    public BasePage clickAboutLink() {
        aboutLink.click();
        return this;
    }

    public BasePage clickResetAppStateLink() {
        resetAppState.click();
        return this;
    }

    public BasePage clickCloseMenuButton() {
        xButton.click();
        return this;
    }
}
