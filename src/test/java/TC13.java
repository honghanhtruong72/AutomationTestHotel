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
import java.time.LocalDateTime;
import java.util.Random;

import utils.DateUtils;

public class TC13 {
    @Test(description = "Verify the booked room information is correctly displayed in My history section")

    public void VerifyBookedRoomInformationIsDisplayedInMyHistorySection() {

        //Login
        homePage.login(Constants.USERNAME, Constants.PASSWORD);

        homePage.openRoomsPage();

        int roomIndex = random.nextInt(roomsPage.getTotalRooms());
        String checkInDate = LocalDate.now().toString();
        String checkOutDate = LocalDate.now().plusDays(1).toString();
        String roomType = roomsPage.getRoomTypeByIndex(roomIndex);

        roomsPage.openRoomByIndex(roomIndex);
        roomDetailsPage.fillBookingForm(checkInDate, checkOutDate, 1, 0);
        bookNowPage.submitUserInfoForm();
        double priceTotal = checkoutPage.getPriceTotal();
        checkoutPage.fillCardDetails(Constants.CARD_NUMBER,
                Constants.CARD_NAME, Constants.EXPIRY_DATE, Constants.CVV);

        String dateTimeBooking = DateUtils.convertToDateAndTime(LocalDateTime.now());

        confirmPage.openMyHistoryPage();
        int indexOfBooking = myHistoryPage.getIndexOfBooking(dateTimeBooking);
        softAssert.assertEquals(myHistoryPage.getTypeRoomByIndex(indexOfBooking), roomType, "Room type is incorrect");
        softAssert.assertEquals(myHistoryPage.getDateCheckInByIndex(indexOfBooking), checkInDate,
                "Check in date is incorrect");
        softAssert.assertEquals(myHistoryPage.getDateCheckOutByIndex(indexOfBooking), checkOutDate,
                "Check out date is incorrect");
        softAssert.assertEquals(myHistoryPage.getAdultNumberByIndex(indexOfBooking), 1, "Adult number is incorrect");
        softAssert.assertEquals(myHistoryPage.getChildrenNumberByIndex(indexOfBooking), 0, "Children number is incorrect");
        //currently price in my history page does not include tax
        softAssert.assertEquals(myHistoryPage.getPriceByIndex(indexOfBooking), priceTotal, "Price total is incorrect");
        softAssert.assertTrue(myHistoryPage.checkCancelButtonByIndex(indexOfBooking), "Cancel button is not displayed");

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
        random = new Random();
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        confirmPage = new ConfirmPage(webDriver);
        searchPage = new SearchPage(webDriver);
        myHistoryPage = new MyHistoryPage(webDriver);
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
    SearchPage searchPage;
    MyHistoryPage myHistoryPage;

}
