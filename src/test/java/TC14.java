import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.hotel.*;
import pages.mail.MailPage;
import utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TC14 {
    @Issues({
            @Issue("Bug02"),
            @Issue("Bug03")
    })
    @Test(
            description = " Verify user can cancel the booked room"
    )
    public void VerifyUserCanCancelTheBookedRoom() {

        idBooking = confirmPage.getBookingId();

        confirmPage.openMyHistoryPage();

        myHistoryPage.openPopUpCancelBookingFromHistoryById(idBooking);

        myHistoryPage.submitCancelBookingForm();
        cancelDate = LocalDate.now();

        softAssert.assertFalse(myHistoryPage.idBookingExists(idBooking), "Booking room still exists in My History page after cancellation");

        myHistoryPage.openCancelledBookingPage();

        softAssert.assertEquals(cancelBookingPage.getTypeRoom(idBooking), roomType, "Room type is incorrect in History page");
        softAssert.assertEquals(cancelBookingPage.getDateCheckIn(idBooking), checkInDate, "Check in date is incorrect in History page");
        softAssert.assertEquals(cancelBookingPage.getDateCheckOut(idBooking), checkOutDate, "Check out date is incorrect in History page");
        softAssert.assertEquals(cancelBookingPage.getDateCancelBooking(idBooking), cancelDate, "Cancel booking date is incorrect in History page");

        ((JavascriptExecutor) webDriver).executeScript("window.open('about:blank','_blank')");
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());

        webDriver.switchTo().window(tabs.get(tabs.size() - 1));

        webDriver.get(Constants.YOPMAIL_URL);

        mailPage.openMail(Constants.MAIL);
        mailPage.openMostRecentMailByTitle(Constants.TITLE_MAIL_CANCEL_BOOKING);


        expectedCancellationCharge = 0.2 * expectedGrandTotal;
        expectedRefundableAmount = expectedGrandTotal - expectedCancellationCharge;

        softAssert.assertEquals(mailPage.getRoomType(), roomType, "Room type in mail is incorrect");
        softAssert.assertEquals(mailPage.getCheckInInCancelMail(), checkInDate, "Check in date in mail is incorrect");
        softAssert.assertEquals(mailPage.getCheckOutInCancelMail(), checkOutDate, "Check out date in mail is incorrect");
        softAssert.assertEquals(mailPage.getCancellationCharge(), expectedCancellationCharge, "Cancellation Charge in mail wrong");
        softAssert.assertEquals(mailPage.getRefundableAmount(), expectedRefundableAmount, "Refundable Amount in mail wrong");
        softAssert.assertAll();

    }

    @BeforeMethod
    @Step("Go to hotel booking page the login and book a room")
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        webDriver = new ChromeDriver(options);
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();

        softAssert = new SoftAssert();
        homePage = new HomePage(webDriver);
        roomsPage = new RoomsPage(webDriver);
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        myAccountPage = new MyAccountPage(webDriver);
        confirmPage = new ConfirmPage(webDriver);
        myHistoryPage = new MyHistoryPage(webDriver);
        cancelBookingPage = new CancelBookingPage(webDriver);
        mailPage = new MailPage(webDriver);
        random = new Random();
        faker = new Faker();

        homePage.login(Constants.USERNAME, Constants.PASSWORD);

        checkInDate = LocalDate.now().plusDays(1);
        checkOutDate = checkInDate.plusDays(1);

        homePage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
        randomNumber = random.nextInt(roomsPage.getTotalRooms());
        roomType = roomsPage.getRoomType(randomNumber);

        roomsPage.openRoomDetailByIndex(randomNumber);
        roomDetailsPage.openBookNowPage();
        expectedGrandTotal = bookNowPage.getGrandTotal();

        bookNowPage.submitUserInfoForm();

        checkoutPage.submitCardDetails(Constants.VALID_CREDIT_CARD);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    WebDriver webDriver;
    String idBooking;
    Double expectedGrandTotal;
    double expectedCancellationCharge;
    double expectedRefundableAmount;
    int randomNumber;

    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    RoomDetailsPage roomDetailsPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    LocalDate cancelDate;
    MyAccountPage myAccountPage;
    ConfirmPage confirmPage;
    MyHistoryPage myHistoryPage;
    MailPage mailPage;
    CancelBookingPage cancelBookingPage;
    Random random;
    String roomType;
    Faker faker;


}
