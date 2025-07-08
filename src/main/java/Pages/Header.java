package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Header {
    private final WebDriver driver;
    private By roomMenuLocator = By.linkText("Rooms");
    private By findingButtonLocator = By.id("sb-search");
    private By searchFieldLocator = By.id("search");

    public void openRoomsPage() {
        driver.findElement(roomMenuLocator).click();
    }

    private void clickFindingButton() {
        driver.findElement(findingButtonLocator).click();
    }

    public void searchBookingNumber(String bookingNumber) {
        clickFindingButton();
        driver.findElement(searchFieldLocator).sendKeys(bookingNumber, Keys.ENTER);
    }

    public Header(WebDriver driver) {
        this.driver = driver;
    }
}
