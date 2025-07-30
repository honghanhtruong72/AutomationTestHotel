import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.hotel.*;
import utils.Constants;
import utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class TC12 {
    @Test(description = "Verify user can view the details of a booked room by booking number")

    public void VerifyUserCanViewDetailsOfBookedRoomByBookingNumber() {

        confirmPage.searchBookingNumber(confirmPage.getBookingId());

        softAssert.assertEquals(searchPage.getRoomType(), Constants.ROOM_TYPE, "Room type is incorrect");
        softAssert.assertEquals(searchPage.getCheckInDate(), checkInDateText,
                "Check in date is incorrect");
        softAssert.assertEquals(searchPage.getCheckOutDate(), checkOutDateText,
                "Check out date is incorrect");
        softAssert.assertEquals(searchPage.getAdultNumber(), 1,
                "Adult number is incorrect");
        softAssert.assertEquals(searchPage.getChildrenNumber(), 0,
                "Children number is incorrect");

        nights = DateUtils.calculateNights(checkInDate, checkOutDate);

        softAssert.assertEquals(searchPage.getNights(), nights, "Nights number is incorrect");
        softAssert.assertEquals(searchPage.getPrice(), priceTotal, "Price total is incorrect");


        softAssert.assertAll();
    }

    @BeforeMethod
    @Step("Go to hotel booking page then book a room")
    public void init() {
        webDriver = new ChromeDriver();
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();

        softAssert = new SoftAssert();
        homePage = new HomePage(webDriver);
        roomsPage = new RoomsPage(webDriver);
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        confirmPage = new ConfirmPage(webDriver);
        searchPage = new SearchPage(webDriver);

        homePage.openRoomsPage();

        checkInDate = LocalDate.now().plusWeeks(1);
        checkOutDate = checkInDate.plusDays(1);
        checkInDateText = checkInDate.format(DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH));
        checkOutDateText = checkOutDate.format(DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH));

        roomsPage.openRoomDetailByRoomType(Constants.ROOM_TYPE);
        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
        bookNowPage.submitUserInfoForm(Constants.FULL_NAME, Constants.MAIL, Constants.PHONE_NUMBER, Constants.ADDRESS);

        priceTotal = checkoutPage.getPriceTotal();
        checkoutPage.submitCardDetails(Constants.VALID_CREDIT_CARD);

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
    LocalDate checkInDate;
    LocalDate checkOutDate;
    double priceTotal;
    int nights;
    String checkInDateText;
    String checkOutDateText;

}
