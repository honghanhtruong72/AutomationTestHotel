package pages.hotel;

import io.qameta.allure.Step;
import model.CreditCard;
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

    @Step("Enter credit card number")
    private void enterCardNumber(String number) {
        driver.findElement(cardNumberLocator).sendKeys(number);
    }

    @Step("Enter credit card expiry date")
    private void enterCardExpiry(String expiry) {
        driver.findElement(cardExpiryLocator).sendKeys(expiry);
    }

    @Step("Enter credit card CVC")
    private void enterCardCvc(String cvc) {
        WebElement cvcField = driver.findElement(cardCvcLocator);
        cvcField.clear();
        cvcField.sendKeys(cvc);
    }

    @Step("Enter credit card name")
    private void enterCardName(String name) {
        driver.findElement(cardNameLocator).sendKeys(name);
    }

    @Step("Click Pay Now button")
    private void clickPayNow() {
        driver.findElement(payNowButtonLocator).click();
    }

    @Step("Make payment with credit card")
    public void submitCardDetails(CreditCard creditCard) {
        submitCardDetails(creditCard.getCardNumber(), creditCard.getCardName(), creditCard.getExpiryDateString(), creditCard.getCvv());
    }

    @Step("Make payment with credit card using the insufficient digits")
    public void submitCardDetailsWrongNumber(String numberCard, CreditCard creditCard) {
        submitCardDetails(numberCard, creditCard.getCardName(), creditCard.getExpiryDateString(), creditCard.getCvv());
    }

    @Step("Make a payment with a credit card using the wrong name")
    public void submitCardDetailsWrongName(String nameCard, CreditCard creditCard) {
        submitCardDetails(creditCard.getCardNumber(), nameCard, creditCard.getExpiryDateString(), creditCard.getCvv());
    }

    private void submitCardDetails(String cardNumber, String cardName, String expiryDate, String cvv) {
        waitForCreditCardForm();
        enterCardNumber(cardNumber);
        enterCardName(cardName);
        enterCardExpiry(expiryDate);
        enterCardCvc(cvv);
        clickPayNow();
    }


    @Step("Get error message for credit card")
    public String getErrorMessageForCreditCard() {
        waitForErrorMessage();
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

    private void waitForErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageForCreditCardLocator));
    }

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

}