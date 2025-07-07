package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Rooms {
    private final WebDriver driver;
    private By viewDetailButtonLocator = By.linkText("View Details");
    private By roomTypeLocator = By.xpath(".//h5");

    public int getTotalRooms() {
        return driver.findElements(viewDetailButtonLocator).size();
    }

    private WebElement getRoomLocatorByIndex(int index) {
        return driver.findElements(viewDetailButtonLocator).get(index);
    }

    public void openRoomByIndex(int index) {
        getRoomLocatorByIndex(index).click();
    }

    private WebElement getRoomDescriptionByIndex(int index) {
        return getRoomLocatorByIndex(index).findElement(By.xpath(
                "./ancestor::div[@class='yemm_hotel_location clearfix']"));
    }

    public String getRoomTypeByIndex(int index) {
        return getRoomDescriptionByIndex(index).findElement(roomTypeLocator).getText();
    }


    public Rooms(WebDriver driver) {
        this.driver = driver;
    }
}
