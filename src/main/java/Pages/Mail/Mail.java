package Pages.Mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Mail {
    private final WebDriver driver;
    private By addressMailLocator = By.id("login");
    private By nextButtonLocator = By.id("refreshbut");
    private By roomTypeLocator = By.xpath("//div[@id='mail']//p[contains(text(), 'Type Room Book')]");
    private By checkInLocator = By.xpath("//div[@id='mail']//p[contains(text(), 'Day Check In')]");
    private By checkOutLocator = By.xpath("//div[@id='mail']//p[contains(text(), 'Day Check Out')]");


    protected By mailLocator (String title) {
        return By.xpath(String.format("//button[@class='lm']/div[@class='lms' and contains(text(), '%s')]", title));
    }

    private WebElement getTotalMailByTitle(String title) {
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

    public void openMostRecentMailByTitle(String title){
        switchToListMail();
        getTotalMailByTitle(title).click();
    }

    public String getRoomType() {
        switchToContentMail();
        String roomTypeText = driver.findElement(roomTypeLocator).getText();
        return roomTypeText.replace("Type Room Book: ", "")
                .replace(",", "")
                .trim();
    }

    private String getMonthNumber(String monthName) {
        switch (monthName) {
            case "Jan": return "01";
            case "Feb": return "02";
            case "Mar": return "03";
            case "Apr": return "04";
            case "May": return "05";
            case "Jun": return "06";
            case "Jul": return "07";
            case "Aug": return "08";
            case "Sep": return "09";
            case "Oct": return "10";
            case "Nov": return "11";
            case "Dec": return "12";
            default: return "00";
        }
    }

    public String getCheckIn() {
        String checkInText = driver.findElement(checkInLocator).getText();
        checkInText = checkInText.replace("Day Check In: ", "")
                .replace(",","").trim();
        String[] parts = checkInText.split(" ");
        String day = parts[2];
        String month = parts[1];
        String year = parts[5];
        String checkInDate = day + "/" + getMonthNumber(month) + "/" + year;
        return checkInDate;
    }

    public String getCheckOut() {
        String checkOutText = driver.findElement(checkOutLocator).getText();
        checkOutText = checkOutText.replace("Day Check Out: ", "")
                .replace(",","").trim();
        String[] parts = checkOutText.split(" ");
        String day = parts[2];
        String month = parts[1];
        String year = parts[5];
        String checkoutDate = day + "/" + getMonthNumber(month) + "/" + year;
        return checkoutDate;
    }

    private void switchToListMail() {
        driver.switchTo().frame("ifinbox");
    }
    private void switchToContentMail() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("ifmail");
    }


    public Mail(WebDriver driver) {
        this.driver = driver;
    }
}
