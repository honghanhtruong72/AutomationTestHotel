package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyHistoryPage {
    private final WebDriver driver;
    private By typeRoomLocator = By.xpath(".//h4");
    private By dateCheckInLocator = By.xpath(".//li[.//strong[contains(text(),'Check-In')]]//p");
    private By dateCheckOutLocator = By.xpath(".//li[.//strong[contains(text(),'Check-Out')]]//p");
    private By adultNumberLocator = By.xpath(".//li//span[contains(text(),'Adult')]");
    private By childNumberLocator = By.xpath(".//li//span[contains(text(),'Children')]");
    private By priceLocator = By.xpath(".//div[@class='price_modual_sec']/strong");
    private By cancelBookingButtonLocator = By.xpath(".//a[text()='Cancel']");

    protected By containerBookingLocator(String id) {
        return By.xpath(String.format("//div[@class='row listing_widgets'][.//strong[contains(text(),'%s')]]", id));
    }

    @Step("Get Type Room")
    public String getTypeRoom(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        return container.findElement(typeRoomLocator).getText();
    }

    @Step("Get Date Check-In")
    public LocalDate getDateCheckInById(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String dateCheckInText = container.findElement(dateCheckInLocator).getText();
        return LocalDate.parse(dateCheckInText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Step("Get Date Check-Out")
    public LocalDate getDateCheckOutById(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String dateCheckOutText = container.findElement(dateCheckOutLocator).getText();
        return LocalDate.parse(dateCheckOutText, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Step("Get Adult Number")
    public int getAdultNumberById(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String adultText = container.findElement(adultNumberLocator).getText();
        return Integer.parseInt(adultText.replace("Adult", "").trim());
    }

    @Step("Get Children Number")
    public int getChildrenNumberById(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String childText = container.findElement(childNumberLocator).getText();
        return Integer.parseInt(childText.replace("Children", "").trim());
    }

    @Step("Get Price")
    public double getPriceById(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        String priceText = container.findElement(priceLocator).getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    @Step("Check Cancel Button is displayed")
    public boolean checkCancelButtonById(String id) {
        WebElement container = driver.findElement(containerBookingLocator(id));
        return container.findElement(cancelBookingButtonLocator).isDisplayed();
    }


    public MyHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

}
