import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.hotel.HomePage;
import pages.hotel.RoomDetailsPage;
import pages.hotel.RoomsPage;
import utils.Constants;

import java.time.LocalDate;
import java.util.Random;

public class TC01 {
    @BeforeMethod
    public void init(){
        webDriver = new ChromeDriver();
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();
        softAssert = new SoftAssert();
        homePage = new HomePage(webDriver);
        roomsPage = new RoomsPage(webDriver);
        random = new Random();
        roomDetailsPage = new RoomDetailsPage(webDriver);
    }

    @Test(description = "Verify room information on Room detail")

    public void VerifyRoomInformationOnRoomDetail(){
        webDriver.get(Constants.HOTEL_BOOKING_URL);

        checkInDate = LocalDate.now().plusMonths(1);

        checkOutDate = checkInDate.plusDays(1);

        homePage.submitBookingForm(checkInDate,checkOutDate,1,0);

        softAssert.assertTrue(roomsPage.hasAvailableRooms(),"No rooms available");

        roomIndex = random.nextInt(roomsPage.getTotalRooms());

        double expectedPrice = roomsPage.getPriceRoomIndex(roomIndex);

        expectedroomType = roomsPage.getRoomTypeByIndex(roomIndex);

        roomsPage.openRoomDetailByIndex(roomIndex);

        softAssert.assertEquals(roomDetailsPage.getRoomType(),expectedroomType,"Room type mismatch");
        softAssert.assertEquals(roomDetailsPage.getDisplayPrice(),expectedPrice,"Room price mismatch");
        softAssert.assertEquals(roomDetailsPage.getCheckInDate(),checkInDate,"Check in date is incorrect");
        softAssert.assertEquals(roomDetailsPage.getCheckOutDate(),checkOutDate,"Check out date is incorrect");










    }

    @AfterMethod
    public void tearDown (){

    }
    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    Random random;
    RoomDetailsPage roomDetailsPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    int roomIndex;
    String expectedroomType;
}
