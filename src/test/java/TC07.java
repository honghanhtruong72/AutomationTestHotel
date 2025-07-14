import io.qameta.allure.Step;
import pages.hotel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.mail.MailPage;
import utils.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TC07 {
    @Test(
            description = "Verify users book room successfully"
    )
    public void VerifyUsersBookRoomSuccessfully() {

        homePage.openRoomsPage();

        roomIndex = random.nextInt(roomsPage.getTotalRooms());
        checkInDate = LocalDate.now().plusMonths(1);
        checkOutDate = checkInDate.plusDays(1);
        roomType = roomsPage.getRoomTypeByIndex(roomIndex);

        roomsPage.openRoomDetailByIndex(roomIndex);
        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
        bookNowPage.submitUserInfoForm(Constants.FULL_NAME,
                Constants.MAIL, Constants.PHONE_NUMBER, Constants.ADDRESS);
        checkoutPage.submitCardDetails(Constants.CARD_NUMBER,
                Constants.CARD_NAME, Constants.EXPIRY_DATE, Constants.CVV);

        softAssert.assertTrue(confirmPage.displaySuccessBookingMessage(Constants.MESSAGE_BOOKING_SUCCESS),
                "Success booking message is not displayed");
        softAssert.assertEquals(confirmPage.getRoomType(), roomType, "Room type is incorrect");
        softAssert.assertEquals(confirmPage.getCheckInDate(), checkInDate, "Check in date is incorrect");
        softAssert.assertEquals(confirmPage.getCheckOutDate(), checkOutDate, "Check out date is incorrect");
        softAssert.assertEquals(confirmPage.getAdultNumber(), 1, "Adult number is incorrect");
        softAssert.assertEquals(confirmPage.getChildrenNumber(), 0, "Children number is incorrect");

        webDriver.switchTo().newWindow(WindowType.TAB);
        webDriver.get(Constants.YOPMAIL_URL);

        mailPage.openMail(Constants.MAIL);
        mailPage.openMostRecentMailByTitle(Constants.TITLE_MAIL_BOOKING_SUCCESS);
        softAssert.assertEquals(mailPage.getRoomType(), roomType, "Room type in mail is incorrect");
        softAssert.assertEquals(mailPage.getCheckIn(), checkInDate, "Check in date in mail is incorrect");
        softAssert.assertEquals(mailPage.getCheckOut(), checkOutDate, "Check out date in mail is incorrect");

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
        roomsPage = new RoomsPage(webDriver);
        random = new Random();
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        confirmPage = new ConfirmPage(webDriver);
        mailPage = new MailPage(webDriver);
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
    ConfirmPage confirmPage;
    MailPage mailPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    String roomType;
    int roomIndex;
}
