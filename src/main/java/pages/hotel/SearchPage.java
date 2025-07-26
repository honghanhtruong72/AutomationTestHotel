package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class SearchPage extends Header {
    private final WebDriver driver;
    private By errorMessageLocator = By.xpath("//h1[@class='mmb-blc-title']");
    private By roomForNightsLocator = By.xpath("//li[@class='text-center']/span");
    private By checkInDateLocator = By.xpath("(//li//p)[1]");
    private By checkOutDateLocator = By.xpath("(//li//p)[2]");
    private By adultNumberLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Adult')]");
    private By childNumberLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Children')]");
    private By priceLocator = By.xpath("//div[@class='receipt_widgets']/strong");
    private By roomTypeLocator = By.xpath("//h5");
    private By messageNoRoomsFoundLocator = By.xpath("//button[contains(text(),'Back')]");

    public boolean checkMessageNoRoomsFoundDisplayed() {
        try {
            return driver.findElement(messageNoRoomsFoundLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    @Step("Get error message displayed on the search page")
    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }

    @Step("Get room type from search results")
    public String getRoomType() {
        return driver.findElement(roomTypeLocator).getText();
    }

    @Step("Get number of nights from search results")
    public int getNights() {
        String nightsText = driver.findElement(roomForNightsLocator).getText()
                .replace("nights", "").trim();
        return Integer.parseInt(nightsText);
    }

    @Step("Get check-in date from search results")
    public String getCheckInDate() {
        String checkInDate = driver.findElement(checkInDateLocator).getText();
        return checkInDate;
    }

    @Step("Get check-out date from search results")
    public String getCheckOutDate() {
        String checkOutDate = driver.findElement(checkOutDateLocator).getText();
        return checkOutDate;
    }

    @Step("Get number of adults from search results")
    public int getAdultNumber() {
        String adultText = driver.findElement(adultNumberLocator).getText();
        return Integer.parseInt(adultText.replace("Adult", "").trim());
    }

    @Step("Get number of children from search results")
    public int getChildrenNumber() {
        String childText = driver.findElement(childNumberLocator).getText();
        return Integer.parseInt(childText.replace("Children", "").trim());
    }

    @Step("Get total price from search results")
    public double getPrice() {
        String priceText = driver.findElement(priceLocator).getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }


    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
