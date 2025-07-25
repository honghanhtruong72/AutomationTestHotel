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

public class TC04 {
    @Test(
            description = "Verify Grand Total Calculation Includes Tax and Discount"
    )
    public void VerifyGrandTotalCalculationIncludesTaxAndDiscount() {

        header.openRoomsPage();

        roomIndex = random.nextInt(roomsPage.getTotalRooms());

        checkInDate = LocalDate.now().plusMonths(1);

        checkOutDate = checkInDate.plusDays(2);

        roomsPage.openRoomDetailByIndex(roomIndex);

        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        bookNowPage.applyPromocode(Constants.INVALID_PROMOCODE);

        actualDiscount = bookNowPage.getDiscount();

        softAssert.assertEquals(actualDiscount, 0.0, "Discount not show be $0.0");

        softAssert.assertTrue(bookNowPage.getDisplayErrorPromotion(), "Error Promocode not display");


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
    double actualDiscount;
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
