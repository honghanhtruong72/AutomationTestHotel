package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private final WebDriver driver;
    private By roomMenuLocator = By.linkText("Rooms");

    public void openRoomsPage() {
        driver.findElement(roomMenuLocator).click();
    }

    public Header(WebDriver driver) {
        this.driver = driver;
    }
}
