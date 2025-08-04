package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RoomsPage {
    private final WebDriver driver;
    private By viewDetailButtonLocator = By.linkText("View Details");
    private By priceRoomLocator = By.xpath(".//strong[@class='green_text']");
    private By containerRoomLocator = By.xpath("//div[@class='most_pop_item_blog clearfix']");
    private By typeRoomLocator = By.xpath(".//h5");


    private WebElement getRoomLocatorByIndex(int index) {
        return driver.findElements(containerRoomLocator).get(index);
    }

    public String getRoomType(int index) {
        return getRoomLocatorByIndex(index).findElement(typeRoomLocator).getText();
    }

    @Step("Open room detail Page by room name")
    public void openRoomDetailByIndex(int index) {
        getRoomLocatorByIndex(index).findElement(viewDetailButtonLocator).click();
    }

    public int getTotalRooms() {
        waitContainerRoom();
        return driver.findElements(containerRoomLocator).size();
    }

    @Step("Check that at least one room is displayed")
    public boolean hasAvailableRooms() {
        return getTotalRooms() > 0;
    }

    @Step("get price room of 1 room")
    public double getPriceRoom(int index) {
        String priceText = getRoomLocatorByIndex(index).findElement(priceRoomLocator).getText()
                .replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    private void waitContainerRoom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(containerRoomLocator));
    }


    public RoomsPage(WebDriver driver) {
        this.driver = driver;
    }
}
