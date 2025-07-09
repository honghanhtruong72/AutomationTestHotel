package Pages.Hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConfirmPage {
    private final WebDriver driver;
    private By titleConfirmLocator = By.xpath("//h2[contains(text(), 'ConfirmPage')]");
    private By roomTypeLocator = By.xpath("//div[@class='row']//h5");
    private By checkInDateLocator = By.xpath("//li[strong[contains(text(), 'Check-In')]]/p");
    private By checkOutDateLocator = By.xpath("//li[strong[contains(text(), 'Check-Out')]]/p");
    private By quantityAdultLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Adult')]");
    private By quantityChildrenLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Children')]");
    private By idBookingLocator = By.xpath("//p[@class='clearfix']/span[2]");

    public ConfirmPage(WebDriver driver) {
        this.driver = driver;
    }

    protected By messageSuccessBookingLocator(String message) {
        return By.xpath(String.format("//div[@class= 'alert alert-success' and contains(., '%s')]", message));

    }


    public boolean displaySuccessBookingMessage(String message) {
        waitOpenConfirmPage();
        return driver.findElement(messageSuccessBookingLocator(message)).isDisplayed();
    }

    private void waitOpenConfirmPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleConfirmLocator));
    }


    public String getRoomType() {
        return driver.findElement(roomTypeLocator).getText();
    }


    public LocalDate getCheckInDate() {
        String checkInDateText = driver.findElement(checkInDateLocator).getText();
        System.out.println("Check-in date text: " + LocalDate.parse(checkInDateText, DateTimeFormatter.ofPattern("MMM dd yyyy")));
        return LocalDate.parse(checkInDateText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }


    public LocalDate getCheckOutDate() {
        String checkOutDateText = driver.findElement(checkOutDateLocator).getText();
        return LocalDate.parse(checkOutDateText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }


    public int getAdultNumber() {
        String adultText = driver.findElement(quantityAdultLocator).getText();
        return Integer.parseInt(adultText.replace("Adult", "").trim());
    }


    public int getChildrenNumber() {
        String childrenText = driver.findElement(quantityChildrenLocator).getText();
        return Integer.parseInt(childrenText.replace("Children", "").trim());
    }

    public String getBookingId() {
        return driver.findElement(idBookingLocator).getText().replace("Id: ", "").trim();
    }


}
