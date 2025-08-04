import io.qameta.allure.Issue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.hotel.*;
import utils.Constants;

import java.time.LocalDate;
import java.util.Random;


public class TC13 {
    @Issue("Bug01")
    @Test(description = "Verify the booked room information is correctly displayed in My history section")

    public void VerifyBookedRoomInformationIsDisplayedInMyHistorySection() {

        confirmPage.openMyHistoryPage();
        softAssert.assertEquals(myHistoryPage.getTypeRoom(idBooking), typeRoom, "Room type is incorrect");
        softAssert.assertEquals(myHistoryPage.getDateCheckIn(idBooking), checkInDate,
                "Check in date is incorrect");
        softAssert.assertEquals(myHistoryPage.getDateCheckOut(idBooking), checkOutDate,
                "Check out date is incorrect");
        softAssert.assertEquals(myHistoryPage.getAdultNumber(idBooking), 1, "Adult number is incorrect");
        softAssert.assertEquals(myHistoryPage.getChildrenNumber(idBooking), 0, "Children number is incorrect");
        softAssert.assertEquals(myHistoryPage.getPrice(idBooking), priceTotal, "Price total is incorrect");
        softAssert.assertTrue(myHistoryPage.checkCancelButton(idBooking), "Cancel button is not displayed");

        softAssert.assertAll();
    }

    @BeforeMethod
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
        confirmPage = new ConfirmPage(webDriver);
        myHistoryPage = new MyHistoryPage(webDriver);
        random = new Random();

        homePage.login(Constants.USERNAME, Constants.PASSWORD);

        checkInDate = LocalDate.now().plusDays(1);
        checkOutDate = checkInDate.plusDays(1);
        homePage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        randomNumber = random.nextInt(roomsPage.getTotalRooms());
        typeRoom = roomsPage.getRoomType(randomNumber);

        roomsPage.openRoomDetailByIndex(randomNumber);
        roomDetailsPage.openBookNowPage();
        bookNowPage.submitUserInfoForm();
        priceTotal = checkoutPage.getPriceTotal();
        checkoutPage.submitCardDetails(Constants.VALID_CREDIT_CARD);
        idBooking = confirmPage.getBookingId();

    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    RoomDetailsPage roomDetailsPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    MyHistoryPage myHistoryPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Random random;
    double priceTotal;
    String idBooking;
    String typeRoom;
    int randomNumber;


}
