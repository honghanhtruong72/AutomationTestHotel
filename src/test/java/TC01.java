import Pages.Hotel.HomePage;
import Pages.Hotel.RoomDetailsPage;
import Pages.Hotel.RoomsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TC01 {
    @BeforeMethod
    public void before() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        rooms = new RoomsPage(driver);
        roomDetails = new RoomDetailsPage(driver);
        random = new Random();
        softAssert = new SoftAssert();

    }

    @Test(description = "Verify room information on Room detail")

    public void VerifyRoomInformationRoomDetail()  {
        //1. Go to http://14.176.232.213:8084/

        driver.get(Constants.HOTEL_BOOKING_URL);

        // 2. Enter checkin date, checkout date, number of adult, number of children
        // 3. Tap on SearchPage button

       // homePage.searchRooms(checkInDate,checkOutDate,adults,children);

        //3: Navigate to room page, The room list shows at least 1 available room

        softAssert.assertTrue(rooms.hasAvailableRooms(), "No rooms available");

        // 4. Tap on random room

         randomIndex = random.nextInt(rooms.getTotalViewDetailButton());

        expectedTypeRooms = rooms.getRoomName(randomIndex);

        expectedPriceRooms = rooms.getPriceRoom(randomIndex);

        rooms.openRoomByIndex(randomIndex);

        // The room information of room matches the previously selected booking

        checkInDate = LocalDate.now().plusMonths(1);

        checkOutDate = checkInDate.plusDays(1);


        softAssert.assertEquals(roomDetails.getTypeRoom(), expectedTypeRooms,"Room type mismatch");

       // softAssert.assertEquals(roomDetails.getDisplayPrice(), expectedPriceRooms,"Room price mismatch");

        softAssert.assertEquals(roomDetails.getDisplayPrice(), expectedPriceRooms, "Room price mismatch");

        softAssert.assertEquals(roomDetails.getCheckInDate(),checkInDate ,"Check in date is incorrect");

        softAssert.assertEquals(roomDetails.getCheckOutDate(), checkOutDate,"Check out date is incorrect");

        softAssert.assertEquals(roomDetails.getAdult(), adults,"Adult number is incorrect");

        softAssert.assertEquals(roomDetails.getChidlren(),children,"Children number is incorrect");


        softAssert.assertAll();

    }

    @AfterMethod
    public void after() {

    }
    LocalDate checkInDate;
    LocalDate checkOutDate;

    int adults = 1;
    int children = 0;
    int randomIndex;
    String expectedTypeRooms;
    Double expectedPriceRooms;

    WebDriver driver;
    HomePage homePage;
    RoomsPage rooms;
    RoomDetailsPage roomDetails;
    SoftAssert softAssert;
    Random random;
}
