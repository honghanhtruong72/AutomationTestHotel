package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ConfirmPage extends Header {
    private final WebDriver driver;
    private By titleConfirmLocator = By.xpath("//h2[contains(text(), 'Confirm')]");
    private By roomTypeLocator = By.xpath("//div[@class='row']//h5");
    private By checkInDateLocator = By.xpath("//li[strong[contains(text(), 'Check-In')]]/p");
    private By checkOutDateLocator = By.xpath("//li[strong[contains(text(), 'Check-Out')]]/p");
    private By quantityAdultLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Adult')]");
    private By quantityChildrenLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Children')]");
    private By idBookingLocator = By.xpath("//p[@class='clearfix']/span[contains( text(),'Id')]");


    protected By messageSuccessBookingLocator(String message) {
        return By.xpath(String.format("//div[@class= 'alert alert-success' and contains(., '%s')]", message));

    }

    @Step("Display success booking message")
    public boolean displaySuccessBookingMessage(String message) {
        waitOpenConfirmPage();
        return driver.findElement(messageSuccessBookingLocator(message)).isDisplayed();
    }

    private void waitOpenConfirmPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleConfirmLocator));
    }

    @Step("Get room type")
    public String getRoomType() {
        return driver.findElement(roomTypeLocator).getText();
    }

    @Step("Get check-in date")
    public LocalDate getCheckInDate() {
        String checkInDateText = driver.findElement(checkInDateLocator).getText();
        return LocalDate.parse(checkInDateText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Step("Get check-out date")
    public LocalDate getCheckOutDate() {
        String checkOutDateText = driver.findElement(checkOutDateLocator).getText();
        return LocalDate.parse(checkOutDateText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Step("Get number of adults")
    public int getAdultNumber() {
        String adultText = driver.findElement(quantityAdultLocator).getText();
        return Integer.parseInt(adultText.replace("Adult", "").trim());
    }

    @Step("Get number of children")
    public int getChildrenNumber() {
        String childrenText = driver.findElement(quantityChildrenLocator).getText();
        return Integer.parseInt(childrenText.replace("Children", "").trim());
    }

    public String getBookingId() {
        return driver.findElement(idBookingLocator).getText().replace("Id: ", "").trim();
    }

    public ConfirmPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
