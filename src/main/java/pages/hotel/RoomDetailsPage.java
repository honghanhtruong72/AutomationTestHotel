package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RoomDetailsPage {
    private final WebDriver driver;
    private By bookingButtonLocator = By.xpath("//input[@value='Book Now']");
    private By checkInDateLocator = By.id("check-in");
    private By checkOutDateLocator = By.id("check-out");
    private By adultQuantityLocator = By.name("adult");
    private By childrenQuantityLocator = By.name("children");
    private By roomTypeLocator = By.xpath("//div[@class='hotel-detail_slider1']/div//h3");
    private By priceLocator = By.xpath("//div[@class='yemm_top_price']/strong");


    @Step("Click on 'Book Now' button")
    public void openBookNowPage() {
        driver.findElement(bookingButtonLocator).click();
    }

    @Step("Get room type")
    public String getRoomType() {
        return driver.findElement(roomTypeLocator).getText();
    }

    @Step("get price room ")
    public double getDisplayPrice() {
        String price = driver.findElement(priceLocator).getText().replace("$", "").trim();
        return Double.parseDouble(price);
    }

    @Step("Get check-in date")
    public LocalDate getCheckInDate() {
        String checkInDateText = driver.findElement(checkInDateLocator).getAttribute("value");
        return LocalDate.parse(checkInDateText, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    @Step("Get check-out date")
    public LocalDate getCheckOutDate() {
        String checkOutDateText = driver.findElement(checkOutDateLocator).getAttribute("value");
        return LocalDate.parse(checkOutDateText, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    @Step("Get Adult")
    public int getAdult() {
        String text = driver.findElement(adultQuantityLocator).getAttribute("value");
        return Integer.parseInt(text);
    }

    @Step("Get Children")
    public int getChildren() {
        String text = driver.findElement(childrenQuantityLocator).getAttribute("value");
        return Integer.parseInt(text);
    }

    public RoomDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
}
