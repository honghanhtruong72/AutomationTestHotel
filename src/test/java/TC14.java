import Pages.Hotel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.util.Random;

public class TC14 {
    @BeforeMethod
    public void before(){
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
        checkout = new CheckoutPage(driver);
        confirm = new ConfirmPage(driver);
        myHistoryPage = new MyHistoryPage(driver);

    }
    @Test(description = "Verify user can cancel the booked room")

    public void TC14(){

//        1. Go to http://14.176.232.213:8084/

        driver.get(Constants.HOTEL_BOOKING_URL);

        header.login(Constants.USERNAME,Constants.PASSWORD);

        homePage.openRoomsPage();

        int roomIndex = random.nextInt(rooms.getTotalRooms());

        rooms.openRoomByIndex(roomIndex);

        roomDetails.submitBookingForm(checkInDate,checkOutDate,adults,children);

       bookNow.fillUserInfoForm("","","","");

        checkout.fillCardDetails(Constants.VALID_CARD_NUMBER,Constants.VALID_CARD_NAME
                                ,Constants.EXPIRY_DATE,Constants.CVV);


//        2. Tap on user name on header
//        3. Select My booking

        header.openMyBookingPage();

//        4. Tap on Cancel button corresponding to the first booked room

           String bookId = confirm.getBookingId();

           myHistoryPage.clickCancelButtonById(bookId);

//        5. Confirm cancellation in the pop-up
//        6. Tap on user name on header
//        7. Select Cancel bookings section
//        8. Check mail

    }
    @AfterClass
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
    CheckoutPage checkout;
    ConfirmPage confirm;
    MyHistoryPage myHistoryPage;
}
