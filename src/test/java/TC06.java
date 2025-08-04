import io.qameta.allure.Step;
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

public class TC06 {
    @Test(
            description = "Verify the Total Amount equal Grand Total of Table summary Booking"
    )
    public void VerifyTheTotalAmountEqualGrandTotalOfTableSummaryBooking() {

        checkInDate = LocalDate.now().plusWeeks(1);

        checkOutDate = checkInDate.plusDays(1);

        homePage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
        int randomNumber = random.nextInt(roomsPage.getTotalRooms());

        roomsPage.openRoomDetailByIndex(randomNumber);
        roomDetailsPage.openBookNowPage();

        expectedPrice = bookNowPage.getGrandTotal();

        bookNowPage.submitUserInfoForm(Constants.FULL_NAME, Constants.MAIL
                , Constants.PHONE_NUMBER, Constants.ADDRESS);

        softAssert.assertEquals(checkoutPage.getPriceTotal(), expectedPrice, "Total Amount not math with GrandTotal");


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
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        random = new Random();
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    double expectedPrice;

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    RoomDetailsPage roomDetailsPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Random random;
}
