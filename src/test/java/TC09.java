import pages.hotel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.LocalDate;
import java.util.Random;

public class TC09 {
    @Test(
            description = "Verify that the system validates card numbers with insufficient digits"
    )
    public void VerifySystemValidatesCardNumbersWithInsufficientDigits() {

        homePage.openRoomsPage();

        int roomIndex = random.nextInt(roomsPage.getTotalRooms());
        String checkInDate = LocalDate.now().toString();
        String checkOutDate = LocalDate.now().plusDays(1).toString();

        roomsPage.openRoomByIndex(roomIndex);

        roomDetailsPage.fillBookingForm(checkInDate, checkOutDate, 1, 0);
        bookNowPage.fillUserInfoForm(Constants.FULL_NAME,
                Constants.MAIL, Constants.PHONE_NUMBER, Constants.ADDRESS);
        checkoutPage.fillCardDetails(Constants.INVALID_CARD_NUMBER,
                Constants.VALID_CARD_NAME, Constants.EXPIRY_DATE, Constants.CVV);

        softAssert.assertEquals(checkoutPage.getErrorMessageForCreditCard(), Constants.ERROR_MESSAGE_CARD_NOT_EXIST,
                "Error message for invalid card number is incorrect");

        softAssert.assertAll();

    }

    @BeforeMethod
    public void init() {
        webDriver = new ChromeDriver();
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();
        softAssert = new SoftAssert();
        homePage = new HomePage(webDriver);
        roomsPage = new RoomsPage(webDriver);
        random = new Random();
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    Random random;
    RoomDetailsPage roomDetailsPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
}
