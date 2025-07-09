import Pages.Hotel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.temporal.ChronoUnit;
import java.util.Random;

public class TC02 {
    @BeforeMethod
    public void before() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        rooms = new RoomsPage(driver);
        roomDetails = new RoomDetailsPage(driver);
        header = new Header(driver);
        bookNow = new BookNowPage(driver);
        random = new Random();
        softAssert = new SoftAssert();
    }

    @Test(description = "Verify Grand Total Calculation Includes Tax and Discount")

    public void VerifyGrandTotalCalculationIncludesTaxAndDiscount() {
        //1. Go to HomePage
        driver.get(Constants.HOTEL_BOOKING_URL);

        //2.2. Click on 'RoomsPage' menu

        header.openRoomsPage();

        //3. Click on 'View Detail'

        int ramdomIndex = random.nextInt(rooms.getTotalViewDetailButton());

        rooms.openRoomByIndex(ramdomIndex);

        //4. Enter booking information then click on Book Now button

        double priceOneNight = roomDetails.getDisplayPrice();

        roomDetails.submitBookingForm(checkInDate, checkOutDate, adults, children);

        //5. Enter promocode

        bookNow.applyPromcode(Constants.Promocode);

        // 6. Calculate expected values

        nights = (int) ChronoUnit.DAYS.between(roomDetails.getCheckInDate(), roomDetails.getCheckOutDate());

        double ExpectedSubTotal = Math.round(nights * priceOneNight * 100.0) / 100.0;

        softAssert.assertEquals(bookNow.getDisplaySubTotal(), ExpectedSubTotal, "Sub Total error compared to formula");

        double tax = bookNow.getDisplayTax();

        double discount = bookNow.getDisplayDiscount();

        double expectedGrandTotal = Math.round((ExpectedSubTotal + tax - discount) * 100.0) / 100.0;

        softAssert.assertEquals(bookNow.getDisplayGrandTotal(), expectedGrandTotal, "The Total formula is applied and gives incorrect result");

        softAssert.assertAll();
    }

    @AfterMethod
    public void after() {

    }

    String checkInDate = "2025-07-20";
    String checkOutDate = "2025-07-22";
    int adults = 1;
    int children = 0;
    int nights;
    WebDriver driver;
    HomePage homePage;
    RoomsPage rooms;
    RoomDetailsPage roomDetails;
    SoftAssert softAssert;
    Random random;
    Header header;
    BookNowPage bookNow;
}
