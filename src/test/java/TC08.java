import io.qameta.allure.Step;
import pages.hotel.HomePage;
import pages.hotel.SearchPage;
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
    public void VerifySystemDisplaysErrorWhenTheGuestSearchNumberBookingNotExists() {

        homePage.searchBookingNumber("000001");
        softAssert.assertEquals(searchPage.getErrorMessage(),
                Constants.ERROR_MESSAGE_BOOKING_NOT_FOUND, "Error message is incorrect");

        softAssert.assertAll();

    }

    @BeforeMethod
    @Step("Go to hotel booking page")
    public void init() {
        webDriver = new ChromeDriver();
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();
        softAssert = new SoftAssert();
        homePage = new HomePage(webDriver);
        searchPage = new SearchPage(webDriver);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    SearchPage searchPage;
}
