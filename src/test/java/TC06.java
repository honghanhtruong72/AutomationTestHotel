import Pages.Hotel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.util.Random;

public class TC06 {
    @BeforeMethod
    public void bf() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        rooms = new RoomsPage(driver);
        roomDetails = new RoomDetailsPage(driver);
        header = new Header(driver);
        bookNow = new BookNowPage(driver);
        random = new Random();
        softAssert = new SoftAssert();
        checkout = new CheckoutPage(driver);
    }

    @Test(description = "Verify the Total Amount equas Grand Total of Table summary Booking")

    public void VerifyTheTotalAmountEquasGrandTotalOfTableSummaryBooking() {
//        1. Go to home http://14.176.232.213:8084/

        driver.get(Constants.HOTEL_BOOKING_URL);

//        2. Click on 'RoomsPage' menu

        homePage.openRoomsPage();

//        3. Click on 'View Detail'

        int roomIndex = random.nextInt(rooms.getTotalRooms());

        rooms.openRoomByIndex(roomIndex);

//        4. Enter booking information then click on Book Now button

        roomDetails.submitBookingForm(checkInDate,checkOutDate,adults,children);

//        5. Enter personal information click on Submit

        double expectedTotalPrice = bookNow.getDisplayGrandTotal();

        bookNow.fillUserInfoForm(Constants.USERNAME,Constants.MAIL,Constants.PHONE_NUMBER,Constants.ADDRESS);

//        6. Observe Total Amount of Payment Page


        double actualTotalAmount = checkout.getDisplayTotalAmount();

        softAssert.assertEquals(actualTotalAmount,expectedTotalPrice,"Total Amount not math with GrandTotal");

        softAssert.assertAll();

    }

    @AfterMethod
    public void after() {

    }
    String checkInDate = "2025-07-20";
    String checkOutDate = "2025-07-22";
    int adults = 1;
    int children = 0;
    WebDriver driver;
    HomePage homePage;
    RoomsPage rooms;
    RoomDetailsPage roomDetails;
    SoftAssert softAssert;
    Random random;
    Header header;
    BookNowPage bookNow;
    CheckoutPage checkout;

}
