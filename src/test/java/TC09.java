import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.util.Random;

public class TC09 {
    @Test(
            description = "Verify that the system validates card numbers with insufficient digits"
    )
    public void VerifySystemValidatesCardNumbersWithInsufficientDigits() {
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();

        home.openRoomsPage();

        int roomIndex = random.nextInt(rooms.getTotalRooms());
        String checkInDate = roomDetails.getToday();
        String checkOutDate = roomDetails.getTomorrowDate();

        rooms.openRoomByIndex(roomIndex);
        roomDetails.fillBookingForm(checkInDate,checkOutDate,
                Constants.ADULT_NUMBER, Constants.CHILD_NUMBER);
        bookNow.fillUserInfoForm(Constants.FULL_NAME,
                Constants.MAIL, Constants.PHONE_NUMBER, Constants.ADDRESS);
        checkout.fillCardDetails(Constants.INVALID_CARD_NUMBER,
                Constants.VALID_CARD_NAME, Constants.EXPIRY_DATE, Constants.CVV);

        softAssert.assertEquals(checkout.getErrorMessageForCreditCard(), Constants.ERROR_MESSAGE_CARD_NOT_EXIST,
                 "Error message for invalid card number is incorrect");

        softAssert.assertAll();

    }

    @BeforeMethod
    public void init() {
        webDriver = new ChromeDriver();
        softAssert = new SoftAssert();
        home = new Home(webDriver);
        rooms = new Rooms(webDriver);
        random = new Random();
        roomDetails = new RoomDetails(webDriver);
        bookNow = new BookNow(webDriver);
        checkout = new Checkout(webDriver);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    WebDriver webDriver;
    SoftAssert softAssert;
    Home home;
    Rooms rooms;
    Random random;
    RoomDetails roomDetails;
    BookNow bookNow;
    Checkout checkout;
}
