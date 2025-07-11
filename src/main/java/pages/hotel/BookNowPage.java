package pages.hotel;

import io.qameta.allure.Step;
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

    @Step("Enter full name: {fullName}")
    private void fillFullName(String fullName) {
        driver.findElement(fullNameLocator).sendKeys(fullName);
    }

    @Step("Enter email: {email}")
    private void fillEmail(String email) {
        driver.findElement(emailLocator).sendKeys(email);
    }

    @Step("Enter phone: {phone}")
    private void fillPhone(String phone) {
        driver.findElement(phoneLocator).sendKeys(phone);
    }

    @Step("Enter address: {address}")
    private void fillAddress(String address) {
        driver.findElement(addressLocator).sendKeys(address);
    }

    @Step("Click checkbox to agree with terms and conditions")
    private void clickCheckBox() {
        driver.findElement(checkBoxLocator).click();
    }

    @Step("Click Submit button")
    private void clickSubmitButton() {
        driver.findElement(submitButtonLocator).click();
    }

    @Step("Submit user information form with full name: {fullName}, email: {email}, phone: {phone}, address: {address}")
    public void submitUserInfoForm(String fullName, String email, String phone, String address) {
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
