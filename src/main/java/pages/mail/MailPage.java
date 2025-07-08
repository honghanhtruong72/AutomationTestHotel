package pages.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class MailPage {
    private final WebDriver driver;
    private By addressMailLocator = By.id("login");
    private By nextButtonLocator = By.id("refreshbut");
    private By roomTypeLocator = By.xpath("//div[@id='mail']//p[contains(text(), 'Type Room Book')]");
    private By checkInLocator = By.xpath("//div[@id='mail']//p[contains(text(), 'Day Check In')]");
    private By checkOutLocator = By.xpath("//div[@id='mail']//p[contains(text(), 'Day Check Out')]");


    protected By mailLocator(String title) {
        return By.xpath(String.format("//button[@class='lm']/div[@class='lms' and contains(text(), '%s')]", title));
    }

    private WebElement getMailByTitle(String title) {
        return driver.findElement(mailLocator(title));
    }

    private void enterAddressMail(String addressMail) {
        driver.findElement(addressMailLocator).sendKeys(addressMail);
    }

    private void clickNextButton() {
        driver.findElement(nextButtonLocator).click();
    }

    public void openMail(String addressMail) {
        enterAddressMail(addressMail);
        clickNextButton();
    }

    public void openMostRecentMailByTitle(String title) {
        switchToListMail();
        getMailByTitle(title).click();
    }

    public String getRoomType() {
        switchToContentMail();
        String roomTypeText = driver.findElement(roomTypeLocator).getText();
        return roomTypeText.replace("Type Room Book: ", "")
                .replace(",", "")
                .trim();
    }

    public String getCheckIn() {
        String checkInText = driver.findElement(checkInLocator).getText();
        checkInText = checkInText.replace("Day Check In: ", "")
                .replace(",", "").trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        TemporalAccessor accessor = formatter.parse(checkInText);
        LocalDate date = LocalDate.from(accessor);
        return date.toString();
    }

    public String getCheckOut() {
        String checkOutText = driver.findElement(checkOutLocator).getText();
        checkOutText = checkOutText.replace("Day Check Out: ", "")
                .replace(",", "").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        TemporalAccessor accessor = formatter.parse(checkOutText);
        LocalDate date = LocalDate.from(accessor);
        return date.toString();
    }

    private void switchToListMail() {
        driver.switchTo().frame("ifinbox");
    }

    private void switchToContentMail() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("ifmail");
    }


    public MailPage(WebDriver driver) {
        this.driver = driver;
    }
}
