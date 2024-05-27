package pages;

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


    public Integer getCountProductsInTheOrder() {
        List<WebElement> order = driver.findElements(CART_ITEM);
        return order.size();
    }

    public void clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();

    }

    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getItemTotal() {
        return driver.findElement(ITEM_TOTAL).getText();
    }

    public String getTax() {
        return driver.findElement(TAX).getText();
    }

    public String getTotal() {
        return driver.findElement(TOTAL).getText();
    }

    public BigDecimal calculateTotalProductsPrice() {
        List<WebElement> actualItems = driver.findElements(PRODUCT_PRICE);
        return actualItems.stream().map(e -> e.getText().replace("$", "")).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalPrice() {
        calculateTotalProductsPrice();
        BigDecimal tax = BigDecimal.valueOf(Double.valueOf(driver.findElement(TAX).getText().replace("Tax: $", "")));
        return calculateTotalProductsPrice().add(tax);
    }

    public String getItemTotalSum() {
        return driver.findElement(ITEM_TOTAL).getText().replace("Item total: $", "");
    }

    public String getTotalSum() {
        return driver.findElement(TOTAL).getText().replace("Total: $", "");
    }


}
