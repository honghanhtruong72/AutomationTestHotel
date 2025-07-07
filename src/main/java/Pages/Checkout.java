package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkout {
    private final WebDriver driver;
    private By cardNumberLocator = By.id("cardNumber");
    private By cardExpiryLocator = By.name("expiry");
    private By cardCvcLocator = By.id("cvvcode");
    private By cardNameLocator = By.id("ownerName");
    private By payNowButtonLocator = By.xpath("//input[@value='Pay Now']");

    private void enterCardNumber(String number) {
        driver.findElement(cardNumberLocator).sendKeys(number);
    }
    private void enterCardExpiry(String expiry) {
        driver.findElement(cardExpiryLocator).sendKeys(expiry);
    }
    private void enterCardCvc(String cvc) {
        WebElement cvcField = driver.findElement(cardCvcLocator);
        cvcField.clear();
        cvcField.sendKeys(cvc);
    }
    private void enterCardName(String name) {
        driver.findElement(cardNameLocator).sendKeys(name);
    }

    private void clickPayNow() {
        driver.findElement(payNowButtonLocator).click();
    }

    public void fillCardDetails(String number, String name, String expiry, String cvc) {
        enterCardNumber(number);
        enterCardName(name);
        enterCardExpiry(expiry);
        enterCardCvc(cvc);
        clickPayNow();
    }

    public Checkout(WebDriver driver) {
        this.driver = driver;
    }
}
