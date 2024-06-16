package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.List;

public class OverviewPage extends BasePage {
    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrice;
    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotal;
    @FindBy(className = "summary_tax_label")
    private WebElement tax;
    @FindBy(className = "summary_total_label")
    private WebElement total;
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    @FindBy(id = "finish")
    private WebElement finishButton;
    @FindBy(className = "cart_item")
    private List<WebElement> cartItem;


    public OverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @Step("Get number of products in the order")
    public Integer getCountProductsInTheOrder() {
        List<WebElement> order = cartItem;
        return order.size();
    }

    @Step("Click 'Cancel' button on the 'Overview' page")
    public ProductsPage clickCancelButton() {
       cancelButton.click();
       return new ProductsPage(driver);

    }

    @Step("Click 'Finish' button on the 'Overview' page")
    public FinishPage clickFinishButton() {
        finishButton.click();
        return new FinishPage(driver);
    }

   @Step("Get 'Item total' sum")
    public String getItemTotal() {
        return itemTotal.getText();
    }

    @Step("Get 'Tax' sum")
    public String getTax() {
        return tax.getText().replace("Tax: $", "");
    }

    @Step("Get 'Total' sum")
    public String getTotal() {
        return total.getText();
    }

    @Step("Get list with all products in the order")
    public List<WebElement> getProductPrice(){
        return productPrice;
    }

    @Step("Get 'Item total' sum (without $)")
    public String getItemTotalSum() {
        return itemTotal.getText().replace("Item total: $", "");
    }

    @Step("Get 'Total' sum (without $)")
    public String getTotalSum() {
        return total.getText().replace("Total: $", "");
    }


}
