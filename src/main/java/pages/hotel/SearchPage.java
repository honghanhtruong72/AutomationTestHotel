package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchPage {
    private final WebDriver driver;
    private By errorMessageLocator = By.xpath("//h1[@class='mmb-blc-title']");
    private By roomForNightsLocator = By.xpath("//li[@class='text-center']/span");
    private By checkInDateLocator = By.xpath("(//li//p)[1]");
    private By checkOutDateLocator = By.xpath("(//li//p)[2]");
    private By adultNumberLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Adult')]");
    private By childNumberLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Children')]");
    private By priceLocator = By.xpath("//div[@class='receipt_widgets']/strong");
    private By roomTypeLocator = By.xpath("//h5");

    @Step("Get error message displayed on the search page")
    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }

    public String getRoomType() {
        return driver.findElement(roomTypeLocator).getText();
    }

    public int getNights() {
        String nightsText = driver.findElement(roomForNightsLocator).getText()
                .replace("nights", "").trim();
        return Integer.parseInt(nightsText);
    }

    public String getCheckInDate() {
        return driver.findElement(checkInDateLocator).getText();
    }

    public String getCheckOutDate() {
        return driver.findElement(checkOutDateLocator).getText();
    }

    public int getAdultNumber() {
        String adultText = driver.findElement(adultNumberLocator).getText();
        return Integer.parseInt(adultText.replace("Adult", "").trim());
    }

    public int getChildrenNumber() {
        String childText = driver.findElement(childNumberLocator).getText();
        return Integer.parseInt(childText.replace("Children", "").trim());
    }

    public double getPrice() {
        String priceText = driver.findElement(priceLocator).getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }


    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }
}
