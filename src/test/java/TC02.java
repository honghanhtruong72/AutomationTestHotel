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

public class TC02 {
    @Test(
            description = "Verify Grand Total Calculation Includes Tax and Discount"
    )
    public void VerifyGrandTotalCalculationIncludesTaxAndDiscount() {

        checkInDate = LocalDate.now().plusWeeks(2);
        checkOutDate = checkInDate.plusDays(1);

        homePage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
        randomNumber = random.nextInt(roomsPage.getTotalRooms());

        roomsPage.openRoomDetailByIndex(randomNumber);
        priceOneNight = roomDetailsPage.getDisplayPrice();
        roomDetailsPage.openBookNowPage();

        bookNowPage.applyPromoCode(Constants.VALID_PROMOCODE);

        night = DateUtils.calculateNights(checkInDate, checkOutDate);

        expectedSubTotal = night * priceOneNight;

        tax = bookNowPage.getTax();

        discount = bookNowPage.getDiscount();

        expectedGrandTotal = expectedSubTotal + tax - discount;
        delta = Math.pow(10, -6);

        softAssert.assertEquals(bookNowPage.getGrandTotal(), expectedGrandTotal, delta, "The Total formula is applied and gives incorrect result");

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
        searchPage = new SearchPage(webDriver);
        random = new Random();
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    int night;
    double priceOneNight;
    double expectedSubTotal;
    double tax;
    double discount;
    double expectedGrandTotal;
    double delta;
    int randomNumber;

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    RoomDetailsPage roomDetailsPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    SearchPage searchPage;
    Random random;
}
