package pages.hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DateUtils;

import java.util.List;

public class MyHistoryPage {
    private final WebDriver driver;
    private By dateTimeBookingLocator = By.xpath("//span[@class='book_date_class']");
    private By typeRoomLocator = By.xpath("//div[@class='col-lg-7 col-md-5 listing-detail_modaul']/h4");
    private By dateChecInLocator = By.xpath("//li[.//strong[contains(text(),'Check-In')]]//p");
    private By dateCheckOutLocator = By.xpath("//li[.//strong[contains(text(),'Check-Out')]]//p");
    private By adultNumberLocator = By.xpath("//li//span[contains(text(),'Adult')]");
    private By childNumberLocator = By.xpath("//li//span[contains(text(),'Children')]");
    private By priceLocator = By.xpath("//div[@class='price_modual_sec']/strong");
    private By cancelBookingButtonLocator = By.linkText("Cancel");


    public int getIndexOfBooking(String bookingDate) {
        List<WebElement> dateElements = driver.findElements(dateTimeBookingLocator);
        int index = -1;

        for (int i = 0; i < dateElements.size(); i++) {
            WebElement span = dateElements.get(i);
            String fullText = span.getText().trim();

            if (fullText.contains(bookingDate)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public String getTypeRoomByIndex(int index) {
        return driver.findElements(typeRoomLocator).get(index).getText();
    }

    public String getDateCheckInByIndex(int index) {
        String dateCheckInText = driver.findElements(dateChecInLocator).get(index).getText();
        return DateUtils.convertToIsoDateWithDate(dateCheckInText);
    }

    public String getDateCheckOutByIndex(int index) {
        String dateCheckOutText = driver.findElements(dateCheckOutLocator).get(index).getText();
        return DateUtils.convertToIsoDateWithDate(dateCheckOutText);
    }

    public int getAdultNumberByIndex(int index) {
        String adultText = driver.findElements(adultNumberLocator).get(index).getText();
        return Integer.parseInt(adultText.replace("Adult", "").trim());
    }

    public int getChildrenNumberByIndex(int index) {
        String childText = driver.findElements(childNumberLocator).get(index).getText();
        return Integer.parseInt(childText.replace("Children", "").trim());
    }

    public double getPriceByIndex(int index) {
        String priceText = driver.findElements(priceLocator).get(index).getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public boolean checkCancelButtonByIndex(int index) {
        return driver.findElements(cancelBookingButtonLocator).get(index).isDisplayed();
    }


    public MyHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

}
