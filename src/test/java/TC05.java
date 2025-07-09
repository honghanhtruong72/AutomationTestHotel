import Pages.Hotel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.util.Random;

public class TC05 {
    @BeforeMethod
    public void before (){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
        header = new Header(driver);
        random = new Random();
        rooms = new RoomsPage(driver);
        roomDetails = new RoomDetailsPage(driver);
        myAccount = new MyAccountPage(driver);
        bookNow = new BookNowPage(driver);

    }
    @Test(description = "Verify auto-filled fields on 'Add your information' form on Book Now Page")
    public void VerifyAutoFilledFieldsOnAddYourInformationFormOnBookNowPage(){
       // 1. Go to http://14.176.232.213:8084/

        driver.get(Constants.HOTEL_BOOKING_URL);

        header.login(Constants.USERNAME,Constants.PASSWORD);

        header.openMyAccountPage();

        //2. Click on 'RoomsPage' menu

        homePage.openRoomsPage();

        //3. Click on 'View Detail'

        int roomIndex = random.nextInt(rooms.getTotalRooms());

        rooms.openRoomByIndex(roomIndex);

        // 4. Enter booking information then click on Book Now button

        roomDetails.submitBookingForm(checkInDate,checkOutDate,adults,children);

        //5. Observe 'Add your information' form

        softAssert.assertEquals(bookNow.getDisplayName(),myAccount.getDisplayName(),"Mismatch in full name");

        softAssert.assertEquals(bookNow.getDisplayEmail(),myAccount.getDisplayEmail(),"Mismatch in Email");

        softAssert.assertEquals(bookNow.getDisplayPhone(),myAccount.getDisplayPhone(),"Mismatch in phone number");

        softAssert.assertEquals(bookNow.getDisplayAdress(),myAccount.getDisplayAdress(),"Mismatch in full name");

        softAssert.assertAll();
    }
    @AfterMethod
    public void after (){

    }
    String checkInDate = "2025-07-20";
    String checkOutDate = "2025-07-22";
    int adults = 1;
    int children = 0;
    WebDriver driver;
    HomePage homePage;
    Header header ;
    SoftAssert softAssert;
    Random random;
    RoomsPage rooms;
    RoomDetailsPage roomDetails;
    MyAccountPage myAccount;
    BookNowPage bookNow;
}
// phải mở trang để so sánh