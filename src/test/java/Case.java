import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Case extends BaseTest {
    @Test
    public void testAddingAnItemToTheCart() {
        driver.navigate().to("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement title = driver.findElement(By.cssSelector("span.title"));
        title.isDisplayed();
        Assert.assertEquals(title.getText(), "Products", "Page with product is opened");

        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("remove-sauce-labs-fleece-jacket")).isDisplayed();
        Assert.assertEquals(driver.findElement(By.id("remove-sauce-labs-fleece-jacket")).getText(), "Remove", "Product has been added to the cart and the button changes to Remove");

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        title.isDisplayed();
        Assert.assertEquals(title.getText(), "Your Cart", "Cart page is opened");
        WebElement name = driver.findElement(By.cssSelector("#item_5_title_link div"));
        Assert.assertEquals(name.getText(), "Sauce Labs Fleece Jacket", "Product name should be: Sauce Labs Fleece Jacket");
        WebElement price = driver.findElement(By.xpath("//a[@id = \"item_5_title_link\"]/following::div[@class= \"inventory_item_price\"]"));
        Assert.assertEquals(price.getText(), "$49.99", "Price should be: $49.99");
    }

}
