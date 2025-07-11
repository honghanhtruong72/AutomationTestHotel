import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.hotel.*;
import utils.Constants;

import java.time.LocalDate;
import java.util.Random;

import utils.DateUtils;

public class TC12 {
    @Test(description = "Verify user can view the details of a booked room by booking number")

    public void VerifyUserCanViewDetailsOfBookedRoomByBookingNumber() {

        homePage.openRoomsPage();

        int roomIndex = random.nextInt(roomsPage.getTotalRooms());
        String checkInDate = LocalDate.now().toString();
        String checkOutDate = LocalDate.now().plusDays(1).toString();
        String roomType = roomsPage.getRoomTypeByIndex(roomIndex);

        roomsPage.openRoomDetailByIndex(roomIndex);
        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
        bookNowPage.submitUserInfoForm(Constants.FULL_NAME, Constants.MAIL, Constants.PHONE_NUMBER, Constants.ADDRESS);

        double priceTotal = checkoutPage.getPriceTotal();
        checkoutPage.submitCardDetails(Constants.CARD_NUMBER, Constants.CARD_NAME, Constants.EXPIRY_DATE, Constants.CVV);

        confirmPage.searchBookingNumber(confirmPage.getBookingId());
        softAssert.assertEquals(searchPage.getRoomType(), roomType, "Room type is incorrect");
        softAssert.assertEquals(searchPage.getCheckInDate(), DateUtils.convertIsoDateToMMdd(checkInDate),
                "Check in date is incorrect");
        softAssert.assertEquals(searchPage.getCheckOutDate(), DateUtils.convertIsoDateToMMdd(checkOutDate), "Check out date is incorrect");
        softAssert.assertEquals(searchPage.getAdultNumber(), 1, "Adult number is incorrect");
        softAssert.assertEquals(searchPage.getChildrenNumber(), 0, "Children number is incorrect");
        softAssert.assertEquals(searchPage.getNights(), DateUtils.getNights(checkInDate, checkOutDate),
                "Nights number is incorrect");
        softAssert.assertEquals(searchPage.getPrice(), priceTotal, "Price total is incorrect");


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
        confirmPage = new ConfirmPage(webDriver);
        searchPage = new SearchPage(webDriver);
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
}
