package pages.hotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private final WebDriver driver;
    private By errorMessageLocator = By.xpath("//h1[@class='mmb-blc-title']");

    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }
}
