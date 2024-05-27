package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.*;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected InfoAboutProductPage infoAboutProductPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected OverviewPage overviewPage;
    protected FinishPage finishPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        infoAboutProductPage = new InfoAboutProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
        finishPage = new FinishPage(driver);

        loginPage.open();
    }
    @BeforeMethod(onlyForGroups  = "userShouldBeLogin")
    public void userShouldBeLogIn() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "name for products")
    public Object[][] productsNameData() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"},
                {"Sauce Labs Onesie"},
                {"Test.allTheThings() T-Shirt (Red)"}
        };
    }

    @DataProvider(name = "data for products")
    public Object[][] productsData() {
        return new Object[][]{
                {"Sauce Labs Backpack", "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", "$29.99"},
                {"Sauce Labs Bike Light", "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.", "$15.99"},
                {"Sauce Labs Fleece Jacket", "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.", "$49.99"},
                {"Sauce Labs Onesie", "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.", "$7.99"},
                {"Test.allTheThings() T-Shirt (Red)", "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.", "$15.99"}
        };
    }
}
