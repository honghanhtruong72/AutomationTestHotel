import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
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
    @Test(
            description = " Verify user can cancel the booked room"
    )
    public void VerifyUserCanCancelTheBookedRoom() {

        header.login(Constants.USERNAME, Constants.PASSWORD);

        header.clickRoom();

        roomIndex = random.nextInt(roomsPage.getTotalRooms());

        checkInDate = LocalDate.now().plusWeeks(2);

        checkOutDate = checkInDate.plusDays(1);

        roomType = roomsPage.getRoomTypeByIndex(roomIndex);

        roomsPage.openRoomDetailByIndex(roomIndex);

        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        expectedGrandTotal = bookNowPage.getGrandTotal();

        bookNowPage.submitUserInfoForm();

        checkoutPage.submitCardDetails(Constants.CARD_NUMBER, Constants.CARD_NAME, Constants.EXPIRY_DATE, Constants.CVV);

        idBooking = confirmPage.getBookingId();

        header.openMyHistoryPage();

        cancelDate = LocalDate.now();

        myHistoryPage.openPopUpcancelBookingFromHistoryById(idBooking);

        myHistoryPage.clickButtonCancelinPopUpCanceBooking(idBooking);

        header.openCancelledBookingPage();


        softAssert.assertEquals(cancelBookingPage.getTypeRoom(idBooking), roomType, "Room type is incorrect");
        softAssert.assertEquals(cancelBookingPage.getDateCheckIn(idBooking), checkInDate, "Check in date is incorrect");
        softAssert.assertEquals(cancelBookingPage.getDateCheckOut(idBooking), checkOutDate, "Check out date is incorrect");
        softAssert.assertEquals(cancelBookingPage.getDateCancelBookig(idBooking), cancelDate, "Cancel booking date is incorrect");


//        webDriver.switchTo().newWindow(WindowType.TAB);

        ((JavascriptExecutor) webDriver).executeScript("window.open('about:blank','_blank')");
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());

        webDriver.switchTo().window(tabs.get(tabs.size()-1));

        webDriver.get(Constants.YOPMAIL_URL);

        mailPage.openMail(Constants.MAIL);
        mailPage.openMostRecentMailByTitle(Constants.TITLE_MAIL_CANCEL_BOOKING);


        expectedCancelationCharge = 0.2 * expectedGrandTotal;
        expectedRefundableAmount = expectedGrandTotal - expectedCancelationCharge;

        softAssert.assertEquals(mailPage.getRoomType(), roomType, "Room type in mail is incorrect");
        softAssert.assertEquals(mailPage.getCheckInInCancelMail(), checkInDate, "Check in date in mail is incorrect");
        softAssert.assertEquals(mailPage.getCheckOutInCancelMail(), checkOutDate, "Check out date in mail is incorrect");
        softAssert.assertEquals(mailPage.getcancelationCharge(), expectedCancelationCharge, "Cancelation Charge in mail wrong");
        softAssert.assertEquals(mailPage.getRefundableAmount(), expectedRefundableAmount, "Refundable Amount in mail wrong");
        softAssert.assertAll();
 w
    }

    @BeforeMethod
    @Step("Go to hotel booking page")
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        webDriver = new ChromeDriver(options);
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();

        softAssert = new SoftAssert();
        homePage = new HomePage(webDriver);
        roomsPage = new RoomsPage(webDriver);
        random = new Random();
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        header = new Header(webDriver);
        myAccountPage = new MyAccountPage(webDriver);
        confirmPage = new ConfirmPage(webDriver);
        myHistoryPage = new MyHistoryPage(webDriver);
        mailPage = new MailPage(webDriver);
        cancelBookingPage = new CancelBookingPage(webDriver);
    }

    @AfterMethod
    public void tearDown() {
//        webDriver.quit();
    }

    int roomIndex;
    String idBooking;
    String roomType;
    Double expectedGrandTotal;
    double expectedCancelationCharge;
    double expectedRefundableAmount;

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    Random random;
    RoomDetailsPage roomDetailsPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    LocalDate cancelDate;
    Header header;
    MyAccountPage myAccountPage;
    ConfirmPage confirmPage;
    MyHistoryPage myHistoryPage;
    MailPage mailPage;
    CancelBookingPage cancelBookingPage;


}
