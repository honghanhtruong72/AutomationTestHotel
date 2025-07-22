package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CancelBookingPage {
    private final WebDriver driver;
    private By typeRoomLocator = By.xpath("//div[@class='row listing_widgets rel_position']//h4");
    private By dateCheckInLocator = By.xpath("(.//li[.//strong[contains(text(),'Check-In')]]//p)");
    private By dateCheckOutLocator = By.xpath("(.//li[.//strong[contains(text(),'Check-Out')]]//p)");
    private By dateCancellocator = By.xpath("(//span[@class='gray_text2']/strong)");

    protected By containerBookingLocator(String bookingId) {
        return By.xpath(String.format("//span[contains(@class,'green_text1') and contains(text(),'%s')]", bookingId));
    }

    @Step("Get Type Room")
    public String getTypeRoom(String datebooking) {
        WebElement container = driver.findElement(containerBookingLocator(datebooking));
        return container.findElement(typeRoomLocator).getText();
    }

    @Step("Get Date Check-In")
    public LocalDate getDateCheckIn(String datebooking) {
        WebElement container = driver.findElement(containerBookingLocator(datebooking));
        String checkOutDateText = container.findElement(dateCheckInLocator).getText();
        return LocalDate.parse(checkOutDateText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Step("Get Date Check-Out")
    public LocalDate getDateCheckOut(String datebooking) {
        WebElement container = driver.findElement(containerBookingLocator(datebooking));
        String checkOutDateText = container.findElement(dateCheckOutLocator).getText();
        return LocalDate.parse(checkOutDateText, DateTimeFormatter.ofPattern("MMMM dd yyyy"));
    }

    @Step("Get Date Cancel Date")
    public LocalDate getDateCancelBookig(String datebooking){
        WebElement container = driver.findElement(containerBookingLocator(datebooking));
        String checkCancelDateText = container.findElement(dateCancellocator).getText();
        return LocalDate.parse(checkCancelDateText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }


    public CancelBookingPage(WebDriver driver) {
        this.driver = driver;
    }
}
