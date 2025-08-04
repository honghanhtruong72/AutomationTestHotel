import io.qameta.allure.Step;
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
    }

    @Test(description = "Verify room information on Room detail")

    public void VerifyRoomInformationOnRoomDetail() {

        checkInDate = LocalDate.now().plusDays(2);

        checkOutDate = checkInDate.plusDays(1);
        homePage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        softAssert.assertTrue(roomsPage.hasAvailableRooms(), "No rooms available");
        int randomNumber = random.nextInt(roomsPage.getTotalRooms());

        expectedPrice = roomsPage.getPriceRoom(randomNumber);
        String roomType = roomsPage.getRoomType(randomNumber);

        roomsPage.openRoomDetailByIndex(randomNumber);

        softAssert.assertEquals(roomDetailsPage.getRoomType(), roomType, "Room type mismatch");
        softAssert.assertEquals(roomDetailsPage.getDisplayPrice(), expectedPrice, "Room price mismatch");
        softAssert.assertEquals(roomDetailsPage.getCheckInDate(), checkInDate, "Check in date is incorrect");
        softAssert.assertEquals(roomDetailsPage.getCheckOutDate(), checkOutDate, "Check out dates is incorrect");
        softAssert.assertEquals(roomDetailsPage.getAdult(), 1, "Adult number is incorrect");
        softAssert.assertEquals(roomDetailsPage.getChildren(), 0, "Children number is incorrect");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    Random random;
    RoomDetailsPage roomDetailsPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    double expectedPrice;
}
