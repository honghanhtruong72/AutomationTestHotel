import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.util.Random;

public class TC07 {
    @Test(
            description = "Verify users book room successfully"
    )
    public void VerifyUsersBookRoomSuccessfully(){
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();

        home.openRoomsPage();

        int roomIndex = random.nextInt(rooms.getTotalRooms());
        String checkInDate = roomDetails.getToday();
        String checkOutDate = roomDetails.getTomorrowDate();
        String roomType = rooms.getRoomTypeByIndex(roomIndex);

        rooms.openRoomByIndex(roomIndex);
        roomDetails.fillBookingForm(checkInDate,checkOutDate,
                Constants.ADULT_NUMBER, Constants.CHILD_NUMBER);
        bookNow.fillUserInfoForm(Constants.FULL_NAME,
                Constants.MAIL, Constants.PHONE_NUMBER, Constants.ADDRESS);
        checkout.fillCardDetails(Constants.CARD_NUMBER,
                Constants.CARD_NAME, Constants.EXPIRY_DATE, Constants.CVV);

        confirm.displaySuccessBookingMessage(Constants.MESSAGE_BOOKING_SUCCESS);
        softAssert.assertEquals(confirm.getRoomType(), roomType, "Room type is incorrect");
        softAssert.assertEquals(confirm.getCheckInDate(),checkInDate, "Check in date is incorrect");
        softAssert.assertEquals(confirm.getCheckOutDate(),checkOutDate, "Check out date is incorrect");
        softAssert.assertEquals(confirm.getAdultNumber(), Constants.ADULT_NUMBER, "Adult number is incorrect");
        softAssert.assertEquals(confirm.getChildrenNumber(), Constants.CHILD_NUMBER, "Children number is incorrect");

        webDriver.switchTo().newWindow(WindowType.TAB);
        webDriver.get(Constants.YOPMAIL_URL);

        mail.openMail(Constants.MAIL);
        mail.openMostRecentMailByTitle(Constants.TITLE_MAIL_BOOKING_SUCCESS);
        softAssert.assertEquals(mail.getRoomType(), roomType, "Room type in mail is incorrect");
        softAssert.assertEquals(mail.getCheckIn(),checkInDate, "Check in date in mail is incorrect");
        softAssert.assertEquals(mail.getCheckOut(),checkOutDate, "Check out date in mail is incorrect");

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
        confirm = new Confirm(webDriver);
        mail = new Mail(webDriver);
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
    Confirm confirm;
    Mail mail;
}
