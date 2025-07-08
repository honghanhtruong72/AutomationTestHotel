import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

public class TC08 {
    @Test(
            description = "Verify system displays an error " +
                    "when the guest search number booking not exists"
    )
    public void VerifySystemDisplaysErrorWhenTheGuestSearchNumberBookingNotExists(){
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();

        home.searchBookingNumber(Constants.INVALID_BOOKING_NUMBER);
        softAssert.assertEquals(search.getErrorMessage(),
                Constants.ERROR_MESSAGE_BOOKING_NOT_FOUND, "Error message is incorrect");

        softAssert.assertAll();

    }

    @BeforeMethod
    public void init() {
        webDriver = new ChromeDriver();
        softAssert = new SoftAssert();
        home = new Home(webDriver);
        search = new Search(webDriver);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    WebDriver webDriver;
    SoftAssert softAssert;
    Home home;
    Search search;
}
