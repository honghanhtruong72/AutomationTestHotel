package pages.hotel;

import org.openqa.selenium.WebDriver;

public class HomePage extends Header{
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
