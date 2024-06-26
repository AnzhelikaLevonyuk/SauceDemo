package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {
    public static WebDriver getDriver( ) throws Exception {
        WebDriver driver;
        String browserName = System.getProperty("browser", PropertyReader.getProperty("browser"));
        String isHeadless = System.getProperty("headless", PropertyReader.getProperty("isHeadless"));
        switch (browserName) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (isHeadless.equals("true")) {
                    options.addArguments("--headless");
                }
                driver = new ChromeDriver(options);
            }
            case "safari" -> driver = new SafariDriver();
            case "firefox" -> driver = new FirefoxDriver();
            default -> throw new Exception("Unsupported browser");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
