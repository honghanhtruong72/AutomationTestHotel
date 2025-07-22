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
import java.util.Random;

public class TC06 {
    @Test(
            description = "Verify the Total Amount equas Grand Total of Table summary Booking"
    )
    public void VerifyTheTotalAmountEquasGrandTotalOfTablSummaryBooking() {

        header.clickRoom();

        roomIndex = random.nextInt(roomsPage.getTotalRooms());

        checkInDate = LocalDate.now().plusMonths(1);

        checkOutDate = checkInDate.plusDays(2);

        roomsPage.openRoomDetailByIndex(roomIndex);

        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        expectedPrice = bookNowPage.getGrandTotal();

        bookNowPage.submitUserInfoForm(Constants.FULL_NAME, Constants.MAIL
                , Constants.PHONE_NUMBER, Constants.ADDRESS);

        softAssert.assertEquals(checkoutPage.getPriceTotal(),expectedPrice,"Total Amount not math with GrandTotal");


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
        header = new Header(webDriver);
    }

    @AfterMethod
    public void tearDown() {
//        webDriver.quit();
    }

    int roomIndex;
    double expectedPrice;

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
    Header header;
}
