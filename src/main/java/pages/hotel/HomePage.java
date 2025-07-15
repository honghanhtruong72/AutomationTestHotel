package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class HomePage extends Header{
    private final WebDriver driver;
    private By checkInDateLocator = By.id("check-in");
    private By checkOutDateLocator = By.id("check-out");
    private By adultQuantityLocator = By.name("adult");
    private By childrenQuantityLocator = By.name("children");
    private By searchButtonLocator = By.xpath("//input[@value='Search']");
    private By timePickerLocator = By.className("ui-datepicker-title");


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    protected By chooseTimeBook(int day, int month, int year) {
        return By.xpath(String.format(
                "//td[@data-handler='selectDay' and @data-month='%d' and @data-year='%d']/a[text()='%d']",
                month - 1, year, day));
    }
    @Step("Click on Check-In date text field")
    private void clickCheckInLocator() {
        driver.findElement(checkInDateLocator).click();
    }

    @Step("Click on Check-Out date text field")
    private void clickCheckOutLocator() {
        driver.findElement(checkOutDateLocator).click();
    }

    @Step("Choose date in time picker")
    private void chooseDateInTimePicker(LocalDate date) {
        waitTimePicker();

        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = date.getDayOfMonth();
        int year = date.getYear();

        navigateToMonthYearInTimePicker(month, year);

        int monthNumber = date.getMonthValue();

        driver.findElement(chooseTimeBook(day, monthNumber, year)).click();
    }

    private void navigateToMonthYearInTimePicker(String month, int year) {

        while (true) {
            String displayedMonthYear = driver.findElement(timePickerLocator).getText();

            if (displayedMonthYear.contains(month) &&
                    displayedMonthYear.contains(String.valueOf(year))) {
                break;
            }

            driver.findElement(By.className("ui-datepicker-next")).click();
        }
    }
    @Step("Enter number of adults")
    private void enterAdultNumber(int adultQuantity) {
        driver.findElement(adultQuantityLocator).clear();
        driver.findElement(adultQuantityLocator).sendKeys(String.valueOf(adultQuantity));
    }
    @Step("Enter number of children")
    private void enterChildrenNumber(int childrenQuantity) {
        driver.findElement(childrenQuantityLocator).clear();
        driver.findElement(childrenQuantityLocator).sendKeys(String.valueOf(childrenQuantity));
    }

    @Step("Click on 'Search' button")
    public void openRoomsPage(){
        driver.findElement(searchButtonLocator).click();
    }
    @Step("Submit booking form")
    public void submitBookingForm(LocalDate dateCheckIn, LocalDate dateCheckOut, int adultQuantity, int childrenQuantity) {
        clickCheckInLocator();
        chooseDateInTimePicker(dateCheckIn);
        clickCheckOutLocator();
        chooseDateInTimePicker(dateCheckOut);
        enterAdultNumber(adultQuantity);
        enterChildrenNumber(childrenQuantity);
        openRoomsPage();
    }
    private void waitTimePicker() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(timePickerLocator));
    }
}
