package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;

public class OverviewPage extends BasePage {
    private final static String ITEM_CONTAINER = "//div[text()='%s']/ancestor::div[@class = 'cart_item_label']";
    private final static By PRODUCT_PRICE = By.className("inventory_item_price");
    private final static By ITEM_TOTAL = By.className("summary_subtotal_label");
    private final static By TAX = By.className("summary_tax_label");
    private final static By TOTAL = By.className("summary_total_label");
    private final static By CANCEL_BUTTON = By.id("cancel");
    private final static By FINISH_BUTTON = By.id("finish");
    private final static By CART_ITEM = By.className("cart_item");


    public OverviewPage(WebDriver driver) {
        super(driver);
    }


    @Step("Get number of products in the order")
    public Integer getCountProductsInTheOrder() {
        List<WebElement> order = driver.findElements(CART_ITEM);
        return order.size();
    }

    @Step("Click 'Cancel' button on the 'Overview' page")
    public void clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();

    }

    @Step("Click 'Finish' button on the 'Overview' page")
    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

   @Step("Get 'Item total' sum")
    public String getItemTotal() {
        return driver.findElement(ITEM_TOTAL).getText();
    }

    @Step("Get 'Tax' sum")
    public String getTax() {
        return driver.findElement(TAX).getText().replace("Tax: $", "");
    }

    @Step("Get 'Total' sum")
    public String getTotal() {
        return driver.findElement(TOTAL).getText();
    }

    @Step("Get list with all products in the order")
    public List<WebElement> getProductPrice(){
        return driver.findElements(PRODUCT_PRICE);
    }

    @Step("Get 'Item total' sum (without $)")
    public String getItemTotalSum() {
        return driver.findElement(ITEM_TOTAL).getText().replace("Item total: $", "");
    }

    @Step("Get 'Total' sum (without $)")
    public String getTotalSum() {
        return driver.findElement(TOTAL).getText().replace("Total: $", "");
    }


}
