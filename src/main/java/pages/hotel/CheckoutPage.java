package pages.hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;
    private By cardNumberLocator = By.id("cardNumber");
    private By cardExpiryLocator = By.name("expiry");
    private By cardCvcLocator = By.id("cvvcode");
    private By cardNameLocator = By.id("ownerName");
    private By payNowButtonLocator = By.xpath("//input[@value='Pay Now']");
    private By errorMessageForCreditCardLocator = By.xpath("//div[@class='dic_msg clear']");
    private By priceTotalLocator = By.xpath("//strong[@class='total_pey']");

    public double getPriceTotal() {
        waitForPriceTotal();
        String priceText = driver.findElement(priceTotalLocator).getText();
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }

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
        waitForCreditCardForm();
        enterCardNumber(number);
        enterCardName(name);
        enterCardExpiry(expiry);
        enterCardCvc(cvc);
        clickPayNow();
    }

    public String getErrorMessageForCreditCard() {
        return driver.findElement(errorMessageForCreditCardLocator).getText();
    }

    private void waitForPriceTotal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceTotalLocator));
    }

    private void waitForCreditCardForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberLocator));
    }

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
}
