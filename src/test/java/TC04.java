import Pages.Hotel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.util.Random;

public class TC04 {
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
    @Test(description = "Ensure the system displays an error when the guest enters the promocode not exists")

    public void EnsureTheSystemDisplaysAnErrorWhenTheGuestEntersThePromocodeNotExists (){

        //1. Go to HomePage

        driver.get(Constants.HOTEL_BOOKING_URL);

        //2.2. Click on 'RoomsPage' menu

        header.openRoomsPage();

        //3. Click on 'View Detail'

        int ramdomIndex = random.nextInt(rooms.getTotalViewDetailButton());

        rooms.openRoomByIndex(ramdomIndex);

        //4. Enter booking information then click on Book Now button

        roomDetails.submitBookingForm(checkInDate,checkOutDate,adults,children);

        //5. Enter promocode

        bookNow.applyPromcode("BT03");

        softAssert.assertTrue(bookNow.isDisplayErrorPromotion(), "Error Promocode not display");

        double actualDiscount = bookNow.getDisplayDiscount();

        softAssert.assertEquals(actualDiscount,0.0,"Discount should be $0.0");

        softAssert.assertAll();
    }
    @AfterMethod
    public void ter(){

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
}
