import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.hotel.*;
import utils.Constants;

import java.time.LocalDate;
import java.util.Random;

public class TC14 {
    @Test(
            description = " Verify user can cancel the booked room"
    )
    public void VerifyUserCanCancelTheBookedRoom() {

        header.login(Constants.USERNAME, Constants.PASSWORD);

        header.clickRoom();

        roomIndex = random.nextInt(roomsPage.getTotalRooms());

        checkInDate = LocalDate.now().plusMonths(2);

        checkOutDate = checkInDate.plusDays(1);

        roomsPage.openRoomDetailByIndex(roomIndex);

        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        bookNowPage.submitUserInfoForm();

        checkoutPage.submitCardDetails(Constants.CARD_NUMBER,Constants.CARD_NAME,Constants.EXPIRY_DATE,Constants.CVV);

        idBooking = confirmPage.getBookingId();

        header.openMyHistoryPage();

        myHistoryPage.cancelBookingFromHistoryById(idBooking);




        softAssert.assertAll();

    }

    @BeforeMethod
    @Step("Go to hotel booking page")
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        webDriver = new ChromeDriver(options);
        webDriver.get(Constants.HOTEL_BOOKING_URL);
        webDriver.manage().window().maximize();

        softAssert = new SoftAssert();
        homePage = new HomePage(webDriver);
        roomsPage = new RoomsPage(webDriver);
        random = new Random();
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        header = new Header(webDriver);
        myAccountPage = new MyAccountPage(webDriver);
        confirmPage = new ConfirmPage(webDriver);
        myHistoryPage = new MyHistoryPage(webDriver);
    }

    @AfterMethod
    public void tearDown() {
//        webDriver.quit();
    }

    int roomIndex;
    String idBooking;

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    Random random;
    RoomDetailsPage roomDetailsPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Header header;
    MyAccountPage myAccountPage;
    ConfirmPage confirmPage;
    MyHistoryPage myHistoryPage;

}
