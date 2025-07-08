package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Search {
    private final WebDriver driver;
    private By errorMessageLocator = By.xpath("//h1[@class='mmb-blc-title']");

    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }

    public Search(WebDriver driver) {
        this.driver = driver;
    }
}
