package pages.hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookNowPage {
    private final WebDriver driver;
    private By fullNameLocator = By.id("name");
    private By emailLocator = By.id("email");
    private By phoneLocator = By.id("phone");
    private By addressLocator = By.id("address");
    private By checkBoxLocator = By.xpath("//label[@class='custom-control custom-checkbox']");
    private By submitButtonLocator = By.xpath("//input[@value='Submit']");

    private void fillFullName(String fullName) {
        driver.findElement(fullNameLocator).sendKeys(fullName);
    }

    private void fillEmail(String email) {
        driver.findElement(emailLocator).sendKeys(email);
    }

    private void fillPhone(String phone) {
        driver.findElement(phoneLocator).sendKeys(phone);
    }

    private void fillAddress(String address) {
        driver.findElement(addressLocator).sendKeys(address);
    }

    private void clickCheckBox() {
        driver.findElement(checkBoxLocator).click();
    }

    private void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    public void fillUserInfoForm(String fullName, String email, String phone, String address) {
        fillFullName(fullName);
        fillEmail(email);
        fillPhone(phone);
        fillAddress(address);
        submitUserInfoForm();
    }

    public void submitUserInfoForm(){
        clickCheckBox();
        clickSubmitButton();
    }


    public BookNowPage(WebDriver driver) {
        this.driver = driver;
    }
}
