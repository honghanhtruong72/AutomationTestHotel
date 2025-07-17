package pages.hotel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private final WebDriver driver;
    private By fullNameLocator = By.id("name");
    private By emailLocator = By.id("email");
    private By phoneLocator = By.id("phone");
    private By addressLocator = By.id("address");
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get FullName")
    public String getFullName (){
      return  driver.findElement(fullNameLocator).getAttribute("value");
    }

    @Step("Get Email")
    public String getEmail (){
       return driver.findElement(emailLocator).getAttribute("value");
    }

    @Step("Get PhoneNumber")
    public String getPhoneNumber (){
      return   driver.findElement(phoneLocator).getAttribute("value");
    }

    @Step("Get getAdress")
    public String getAdress (){
      return  driver.findElement(addressLocator).getAttribute("value");
    }


}
