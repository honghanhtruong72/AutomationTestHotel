import Pages.Hotel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.util.Random;

public class TC03 {
    @BeforeMethod
    public void before(){
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
    @Test(description = "Verify Grand Total Calculation without discount")

    public void VerifyGrandTotalCalculationWithoutDiscount (){

        //1. Go to HomePage
        driver.get(Constants.HOTEL_BOOKING_URL);

        //2.2. Click on 'RoomsPage' menu
        header.openRoomsPage();

        //3. Click on 'View Detail'
        int ramdomIndex = random.nextInt(rooms.getTotalViewDetailButton());
        rooms.openRoomByIndex(ramdomIndex);

        //4. Enter booking information then click on Book Now button

        double priceonenight = roomDetails.getDisplayPrice();

        roomDetails.submitBookingForm(checkInDate,checkOutDate,adults,children);

        //5. Make sure the promocode fields is empty

        double discount = bookNow.getDisplayDiscount();

        softAssert.assertEquals(discount,0.0,"Discount should be $0");

        int nightDay = roomDetails.calculateNights(checkInDate,checkOutDate);


        double expectedSubTotal = Math.round(nightDay*priceonenight*100.0)/100.0;

        double actualSubTotal = bookNow.getDisplaySubTotal();

        double tax = bookNow.getDisplayTax();

        double expectedGrandTotal = Math.round((expectedSubTotal+tax)*100.0)/100.0;

        double actualGrandTotal = bookNow.getDisplayGrandTotal();

        softAssert.assertEquals(actualSubTotal, expectedSubTotal, "Sub Total mismatch");

        softAssert.assertEquals(actualGrandTotal, expectedGrandTotal, "Grand Total calculation incorrect");


        softAssert.assertAll();

    }
    @AfterMethod
    public void after (){

    }
    String checkInDate = "2025-07-23";
    String checkOutDate = "2025-07-25";
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
}
