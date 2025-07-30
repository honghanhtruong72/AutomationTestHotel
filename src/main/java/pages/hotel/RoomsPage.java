package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RoomsPage {
    private final WebDriver driver;
    private By viewDetailButtonLocator = By.linkText("View Details");
    private By priceRoomLocator = By.xpath(".//strong[@class='green_text']");
    private By containerRoomLocator = By.xpath("//div[@class='most_pop_item_blog clearfix']");

    protected By containerRoomLocatorByRoomType(String name) {
        return By.xpath(String.format("//div[@class='most_pop_item_blog clearfix'][.//h5[contains(text(),'%s')]]", name));
    }

    private WebElement getRoomLocatorByRoomType(String name) {
        return driver.findElement(containerRoomLocatorByRoomType(name));
    }

    @Step("Open room detail Page by room name")
    public void openRoomDetailByRoomType(String name) {
        getRoomLocatorByRoomType(name).findElement(viewDetailButtonLocator).click();
    }

    public int getTotalRooms() {
        return driver.findElements(containerRoomLocator).size();
    }

    @Step("Check that at least one room is displayed")
    public boolean hasAvailableRooms() {
        return getTotalRooms() > 0;
    }

    @Step("get price room of 1 room")
    public double getPriceRoom(String name) {
        WebElement container = driver.findElement(containerRoomLocatorByRoomType(name));
        String priceText = container.findElement(priceRoomLocator).getText()
                .replace("$", "").trim();
        return Double.parseDouble(priceText);
    }


    public RoomsPage(WebDriver driver) {
        this.driver = driver;
    }
}
