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

public class TC03 {
    @Test(
            description = "Verify Grand Total Calculation without discount"
    )
    public void VerifyGrandTotalCalculationWithoutDiscount() {

        header.clickRoom();

        roomIndex = random.nextInt(roomsPage.getTotalRooms());

        checkInDate = LocalDate.now().plusMonths(1);

        checkOutDate = checkInDate.plusDays(2);

        roomsPage.openRoomDetailByIndex(roomIndex);

        priceOneNight = roomDetailsPage.getDisplayPrice();

        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        night = DateUtils.calculateNights(checkInDate, checkOutDate);

        ExpectedSubTotal = Math.round(night * priceOneNight * 100.0) / 100.0;

        tax = bookNowPage.getTax();

        discount = bookNowPage.getDiscount();

        expectedGrandTotal = Math.round((ExpectedSubTotal + tax ) * 100.0) / 100.0;

        softAssert.assertEquals(bookNowPage.getDiscount(),0.0,"Discount not show be $0.0");

        softAssert.assertEquals(bookNowPage.getGrandTotal(), expectedGrandTotal, "The Total formula is applied and gives incorrect result");

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
          webDriver.quit();
    }

    int nights;
    int roomIndex;
    long night;
    double priceOneNight;
    double ExpectedSubTotal;
    double tax;
    double discount;
    double expectedGrandTotal;

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
