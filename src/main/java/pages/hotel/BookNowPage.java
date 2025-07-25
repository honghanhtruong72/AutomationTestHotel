package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookNowPage {
    private final WebDriver driver;
    private By fullNameLocator = By.id("name");
    private By emailLocator = By.id("email");
    private By phoneLocator = By.id("phone");
    private By addressLocator = By.id("address");
    private By checkBoxLocator = By.xpath("//label[@class='custom-control custom-checkbox']");
    private By submitButtonLocator = By.xpath("//input[@value='Submit']");
    private By promocodeRadioButtonLocator = By.xpath("//label[@class='custom-control custom-radio m-0']");
    private By promocodelocator = By.id("code");
    private By buttonApplyLocator = By.xpath("//button[@type='submit']");
    private By subTotalLocator = By.xpath("//div[@class='row bookin_detail_tabel']//table//tr[2]/td[2]/strong");
    private By taxLocator = By.xpath("//td[contains(text(), 'Tax')]/following-sibling::td");
    private By discountLocator = By.xpath("//td[contains(text(), 'Discount')]/following-sibling::td");
    private By grandTotalLocator = By.xpath("//table/tbody/tr[5]/td[2]/strong");
    private By errorPromotion = By.xpath("//p[contains(text(), 'Promotion Code not exists')]");



    @Step("Enter full name")
    private void fillFullName(String fullName) {
        driver.findElement(fullNameLocator).sendKeys(fullName);
    }

    @Step("Enter email")
    private void fillEmail(String email) {
        driver.findElement(emailLocator).sendKeys(email);
    }

    @Step("Enter phone")
    private void fillPhone(String phone) {
        driver.findElement(phoneLocator).sendKeys(phone);
    }

    @Step("Enter address")
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

    @Step("Submit user information form")
    public void submitUserInfoForm(String fullName, String email, String phone, String address) {
        fillFullName(fullName);
        fillEmail(email);
        fillPhone(phone);
        fillAddress(address);
        submitUserInfoForm();
    }

    @Step("Click Submit Button")
    public void submitUserInfoForm() {
        clickCheckBox();
        clickSubmitButton();
    }

    @Step("Click Promocode Radio")
    public void clickRadioButtonPromcode(){
        driver.findElement(promocodeRadioButtonLocator).click();
    }

    @Step("Enter Promocode")
    public void enterTextboxPromcode(String promocode){
        driver.findElement(promocodelocator).sendKeys(promocode);
    }

    @Step("Click Apply Button")
    public void  clickbuttonApply (){
        driver.findElement(buttonApplyLocator).click();
    }

    @Step("Aplly Promocode")
    public void applyPromocode(String a){
        clickRadioButtonPromcode();
        enterTextboxPromcode(a);
        clickbuttonApply();
    }
    @Step("Get Subtotal")
    public double getSubTotal (){
        String text = driver.findElement(subTotalLocator).getText().replace("$","");
        return Double.parseDouble(text);
    }

    @Step("Get Tax")
    public double getTax(){
        String text = driver.findElement(taxLocator).getText().replace("$","");
        return Double.parseDouble(text);
    }

    @Step("Get Discount")
    public double getDiscount (){
        String text = driver.findElement(discountLocator).getText().replace("$","");
        return Double.parseDouble(text);
    }

    @Step("Get Grand Total")
    public double getGrandTotal (){
        waitGrandTotalDisplay();
        String text = driver.findElement(grandTotalLocator).getText().replace("$","");
        return Double.parseDouble(text);
    }

    public void waitGrandTotalDisplay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(grandTotalLocator));
    }

    @Step("Get Display Error Promotion")
    public boolean getDisplayErrorPromotion(){
        driver.findElement(errorPromotion).isDisplayed();
        return true;
    }

    @Step("Get Value FullName")
    public String getFullNameTextBoxValue(){
        return  driver.findElement(fullNameLocator).getAttribute("value");
    }

    @Step("Get Value Email")
    public String getEmailTextBoxValue(){
        return driver.findElement(emailLocator).getAttribute("value");
    }

    @Step("Get Value PhoneNumber")
    public String getPhoneTextBoxValue(){
        return   driver.findElement(phoneLocator).getAttribute("value");
    }

    @Step("Get Value getAdress")
    public String getAdressTextBoxValue(){
        return  driver.findElement(addressLocator).getAttribute("value");
    }

    public BookNowPage(WebDriver driver) {
        this.driver = driver;
    }
}
