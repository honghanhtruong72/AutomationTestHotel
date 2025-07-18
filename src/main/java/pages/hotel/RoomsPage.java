package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RoomsPage {
    private final WebDriver driver;
    private By viewDetailButtonLocator = By.linkText("View Details");
    private By roomTypeLocator = By.xpath(".//h5");
    private By containerRoomsLocator = By.xpath(
            "//div[@class='most_pop_item_blog clearfix']");

    public int getTotalRooms() {
        return driver.findElements(containerRoomsLocator).size();
    }

    private WebElement getRoomLocatorByIndex(int index) {
        return driver.findElements(containerRoomsLocator).get(index);
    }

    @Step("Open room detail")
    public void openRoomDetailByIndex(int index) {
        getRoomLocatorByIndex(index).findElement(viewDetailButtonLocator).click();
    }

    @Step("Get room type")
    public String getRoomTypeByIndex(int index) {
        return getRoomLocatorByIndex(index).findElement(roomTypeLocator).getText();
    }


    public RoomsPage(WebDriver driver) {
        this.driver = driver;
    }
}
