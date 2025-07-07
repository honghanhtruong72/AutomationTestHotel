package Pages;

import org.openqa.selenium.WebDriver;

public class Home extends Header{
    private final WebDriver driver;

    public Home(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
