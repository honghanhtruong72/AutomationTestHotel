package pages.hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class RoomDetailsPage {
    private final WebDriver driver;
    private By bookingButtonLocator = By.xpath("//input[@value='Book Now']");
    private By checkInDateLocator = By.id("check-in");
    private By checkOutDateLocator = By.id("check-out");
    private By adultQuantityLocator = By.name("adult");
    private By childrenQuantityLocator = By.name("children");
    private By timePickerLocator = By.className("ui-datepicker-title");

    protected By chooseTimeBook(int day, int month, int year) {
        return By.xpath(String.format(
                "//td[@data-handler='selectDay' and @data-month='%d' and @data-year='%d']/a[text()='%d']",
                month - 1, year, day));
    }

    private void chooseCheckInTimePicker(String dateCheckIn) {
        driver.findElement(checkInDateLocator).click();
        waitTimePicker();
        String[] parts = dateCheckIn.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        navigateToMonthYearInTimePicker(month, year);

        driver.findElement(chooseTimeBook(day, month, year)).click();
    }

    private void chooseCheckOutTimePicker(String dateCheckOut) {
        driver.findElement(checkOutDateLocator).click();
        waitTimePicker();
        String[] parts = dateCheckOut.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        navigateToMonthYearInTimePicker(month, year);

        driver.findElement(chooseTimeBook(day, month, year)).click();
    }

    private void navigateToMonthYearInTimePicker(int month, int year) {
        while (true) {
            String displayedMonthYear = driver.findElement(timePickerLocator).getText();

            if (displayedMonthYear.contains(getMonthName(month)) &&
                    displayedMonthYear.contains(String.valueOf(year))) {
                break;
            }

            driver.findElement(By.className("ui-datepicker-next")).click();
        }
    }

    private String getMonthName(int month) {
        return Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    private void enterAdultNumber(int adultQuantity) {
        driver.findElement(adultQuantityLocator).clear();
        driver.findElement(adultQuantityLocator).sendKeys(String.valueOf(adultQuantity));
    }

    private void enterChildrenNumber(int childrenQuantity) {
        driver.findElement(childrenQuantityLocator).clear();
        driver.findElement(childrenQuantityLocator).sendKeys(String.valueOf(childrenQuantity));
    }

    public void openBookNowPage() {
        driver.findElement(bookingButtonLocator).click();
    }

    public void fillBookingForm(String dateCheckIn, String dateCheckOut, int adultQuantity, int childrenQuantity) {
        chooseCheckInTimePicker(dateCheckIn);
        chooseCheckOutTimePicker(dateCheckOut);
        enterAdultNumber(adultQuantity);
        enterChildrenNumber(childrenQuantity);
        openBookNowPage();
    }


    private void waitTimePicker() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(timePickerLocator));
    }


    public RoomDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
}
