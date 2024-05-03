package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinishPage extends BasePage {
    private final static By BACK_HOME_BUTTON = By.id("back-to-products");
    private final static By TICK_ICON = By.className("pony_express");
    private final static By COMPLETE_HEADER = By.className("complete-header");
    private final static By COMPLETE_TEXT = By.className("complete-text");


    public FinishPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTickIconDisplayed() {
        return driver.findElement(TICK_ICON).isDisplayed();
    }

    public boolean isCompleteHeaderDisplayed() {
        return driver.findElement(COMPLETE_HEADER).isDisplayed();
    }

    public boolean isCompleteTextDisplayed() {
        return driver.findElement(COMPLETE_TEXT).isDisplayed();
    }

    public boolean isBackHomeButtonDisplayed() {
        return driver.findElement(BACK_HOME_BUTTON).isDisplayed();
    }

    public void clickBackHomeButton() {
        driver.findElement(BACK_HOME_BUTTON).click();
    }
}
