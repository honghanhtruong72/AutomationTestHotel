package pages.hotel;

import dto.RoomDetailData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.Random;

public class RoomsPage {
    private final WebDriver driver;
    private By viewDetailButtonLocator = By.linkText("View Details");
    private By roomTypeLocator = By.xpath(".//h5");
    private By containerRoomsLocator = By.xpath(
            "//div[@class='most_pop_item_blog clearfix']");
    private By priceRoomLocator = By.xpath(".//strong[@class='green_text']");

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

    @Step("Ensure open room detail")
    public RoomDetailData ensureOpenRoomDetailByIndex(RoomDetailsPage roomDetailsPage, SearchPage searchPage,
                                                      LocalDate checkInDate, LocalDate checkOutDate) {
        int maxTry = 10;
        int count = 0;
        int roomIndex = 1;
        double priceOneNight = 0.0;
        String roomType = "";
        Random random = new Random();
        int totalRooms = getTotalRooms();
        while (count < maxTry) {
            roomIndex = random.nextInt(totalRooms);
            roomType = getRoomTypeByIndex(roomIndex);
            openRoomDetailByIndex(roomIndex);

            priceOneNight = roomDetailsPage.getDisplayPrice();

            roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
            if (!searchPage.checkMessageNoRoomsFoundDisplayed()) {
                break;
            }
            count++;
            searchPage.openRoomsPage();
        }
        return new RoomDetailData(roomIndex, count, priceOneNight, roomType);
    }

    @Step("Get room type")
    public String getRoomTypeByIndex(int index) {
        return getRoomLocatorByIndex(index).findElement(roomTypeLocator).getText();
    }

    @Step("Check that at least one room is displayed")
    public boolean hasAvailableRooms() {
        return getTotalRooms() > 0;
    }

    @Step("get price room of 1 room random")
    public double getPriceRoomIndex(int index) {
        String price = getRoomLocatorByIndex(index).findElement(priceRoomLocator).getText()
                .replace("$", "").trim();
        return Double.parseDouble(price);
    }


    public RoomsPage(WebDriver driver) {
        this.driver = driver;
    }
}
