package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Confirm {
    private final WebDriver driver;
    private By titleConfirmLocator = By.xpath("//h2[contains(text(), 'Confirm')]");
    private By roomTypeLocator = By.xpath("//div[@class='row']//h5");
    private By checkInDateLocator = By.xpath("//li[strong[contains(text(), 'Check-In')]]/p");
    private By checkOutDateLocator = By.xpath("//li[strong[contains(text(), 'Check-Out')]]/p");
    private By quantityAdultLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Adult')]");
    private By quantityChildrenLocator = By.xpath("//p[@class='getsts_cont']/span[contains(text(),'Children')]");

    protected By messageSuccessBookingLocator(String message) {
        return By.xpath(String.format("//div[@class= 'alert alert-success' and contains(., '%s')]",message));

    }

    public boolean displaySuccessBookingMessage(String message) {
        waitOpenConfirmPage();
        return driver.findElement(messageSuccessBookingLocator(message)).isDisplayed();
    }

    private void waitOpenConfirmPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleConfirmLocator));
    }

    public String getRoomType() {
        return driver.findElement(roomTypeLocator).getText();
    }
    public String getCheckInDate() {
        String checkInDateText = driver.findElement(checkInDateLocator).getText();
        return convertDate(checkInDateText);
    }
    public String getCheckOutDate() {
        String checkOutDateText = driver.findElement(checkOutDateLocator).getText();
        return convertDate(checkOutDateText);
    }
    public int getAdultNumber() {
        String adultText = driver.findElement(quantityAdultLocator).getText();
        return Integer.parseInt(adultText.replace("Adult", "").trim());
    }
    public int getChildrenNumber() {
        String childrenText = driver.findElement(quantityChildrenLocator).getText();
        return Integer.parseInt(childrenText.replace("Children", "").trim());
    }

    public static String convertDate(String dateString){
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Confirm(WebDriver driver) {
        this.driver = driver;
    }
}
