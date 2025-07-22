package pages.hotel;

import io.qameta.allure.Step;
import jdk.jfr.StackTrace;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyHistoryPage {
    private final WebDriver driver;
    private By typeRoomLocator = By.xpath(".//h4");
    private By dateCheckInLocator = By.xpath(".//li[.//strong[contains(text(),'Check-In')]]//p");
    private By dateCheckOutLocator = By.xpath(".//li[.//strong[contains(text(),'Check-Out')]]//p");
    private By adultNumberLocator = By.xpath(".//li//span[contains(text(),'Adult')]");
    private By childNumberLocator = By.xpath(".//li//span[contains(text(),'Children')]");
    private By priceLocator = By.xpath(".//div[@class='price_modual_sec']/strong");
    private By cancelBookingButtonLocator = By.xpath(".//a[text()='Cancel']");
    private By cancelPopupButton = By.xpath("//form[.//input[@name='bookingId']]//input[@type='submit' and @value='Cancel']");
    private By bookingDateLocator = By.xpath("//span[@class='book_date_class']/strong");

    protected By containerBookingLocator(String id) {
        return By.xpath(String.format("//div[@class='row listing_widgets'][.//strong[contains(text(),'%s')]]", id));
    }

    @Step("Get Type Room")
    public String getTypeRoom(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        return container.findElement(typeRoomLocator).getText();
    }

    @Step("Get Date Check-In")
    public LocalDate getDateCheckIn(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String dateCheckInText = container.findElement(dateCheckInLocator).getText();
        return LocalDate.parse(dateCheckInText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Step("Get Date Check-Out")
    public LocalDate getDateCheckOut(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String dateCheckOutText = container.findElement(dateCheckOutLocator).getText();
        return LocalDate.parse(dateCheckOutText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Step("Get Adult Number")
    public int getAdultNumber(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String adultText = container.findElement(adultNumberLocator).getText();
        return Integer.parseInt(adultText.replace("Adult", "").trim());
    }

    @Step("Get Children Number")
    public int getChildrenNumber(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String childText = container.findElement(childNumberLocator).getText();
        return Integer.parseInt(childText.replace("Children", "").trim());
    }

    @Step("Get Price")
    public double getPrice(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String priceText = container.findElement(priceLocator).getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    @Step("Check Cancel Button is displayed")
    public boolean checkCancelButton(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        return container.findElement(cancelBookingButtonLocator).isDisplayed();
    }

    @Step("Click Button Cancel")
    public void openPopUpcancelBookingFromHistoryById(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        container.findElement(cancelBookingButtonLocator).click();
    }

    @Step("Click Button Cancel In Popup CancelBooking")
    public void clickButtonCancelinPopUpCanceBooking(String id) {
        waitUntilCancelPopupAppears();
        WebElement form = driver.findElement(containerBookingLocator(id));
        form.findElement(cancelPopupButton).click();

    }

    private void waitUntilCancelPopupAppears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(cancelPopupButton));
    }

    @Step("Get Booking Date")
    public String getDateTimeBooking(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        return container.findElement(bookingDateLocator).getText().trim();
    }


    public MyHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

}
