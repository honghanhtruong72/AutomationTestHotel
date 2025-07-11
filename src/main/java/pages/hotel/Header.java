package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver driver;
    private By roomMenuLocator = By.linkText("Rooms");
    private By findingButtonLocator = By.id("sb-search");
    private By searchFieldLocator = By.id("search");
    private By loginButtonLocator = By.linkText("Login");
    private By userNameTextBoxLocator = By.name("email");
    private By passwordTextBoxLocator = By.id("password");
    private By SignInButtonLocator = By.xpath("//input[@value='Sign In']");
    private By userNameLocator = By.id("NavebarProfileDrop");
    private By myBookingsLocator = By.linkText("My Bookings");
    private By cancelBookingLocator = By.linkText("Cancel Bookings");

    private void openDropDownFromUserName() {
        waitForUserName();
        driver.findElement(userNameLocator).click();
    }

    public void openMyHistoryPage() {
        openDropDownFromUserName();
        driver.findElement(myBookingsLocator).click();
    }

    public void openCancelBookingPage() {
        openDropDownFromUserName();
        driver.findElement(cancelBookingLocator).click();
    }

    public void openRoomsPage() {
        driver.findElement(roomMenuLocator).click();
    }

    private void clickFindingButton() {
        driver.findElement(findingButtonLocator).click();
    }

    @Step("Search for booking number: {bookingNumber}")
    public void searchBookingNumber(String bookingNumber) {
        clickFindingButton();
        driver.findElement(searchFieldLocator).sendKeys(bookingNumber, Keys.ENTER);
    }

    private void enterUserName(String userName) {
        driver.findElement(userNameTextBoxLocator).sendKeys(userName);
    }

    private void enterPassword(String password) {
        driver.findElement(passwordTextBoxLocator).sendKeys(password);
    }

    private void clickSignInButton() {
        driver.findElement(SignInButtonLocator).click();
    }

    private void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    public void login(String userName, String password) {
        clickLoginButton();
        waitForLoginPopUp();
        enterUserName(userName);
        enterPassword(password);
        clickSignInButton();
    }

    private void waitForLoginPopUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameTextBoxLocator));
    }

    private void waitForUserName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLocator));
    }

    public Header(WebDriver driver) {
        this.driver = driver;
    }
}
