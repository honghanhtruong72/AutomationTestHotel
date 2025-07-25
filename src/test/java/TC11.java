import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.hotel.*;
import utils.Constants;

import java.time.LocalDate;
import java.util.Random;

public class TC11 {
    @Test(
            description = "Verify user can't book room when credit card is not enough money"
    )
    public void VerifyUserCanNotBookRoomWhenCreditCardIsNotEnoughMoney() {

        homePage.openRoomsPage();

        roomIndex = random.nextInt(roomsPage.getTotalRooms());
        checkInDate = LocalDate.now().plusWeeks(1);
        checkOutDate = checkInDate.plusDays(1);

        roomsPage.openRoomDetailByIndex(roomIndex);
        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
        bookNowPage.submitUserInfoForm(Constants.FULL_NAME,
                Constants.MAIL, Constants.PHONE_NUMBER, Constants.ADDRESS);
        checkoutPage.submitCardDetails(Constants.CREDIT_CARD_NO_MONEY);

        softAssert.assertEquals(checkoutPage.getErrorMessageForCreditCard(), Constants.ERROR_MESSAGE_CARD_NOT_MONEY,
                "The error message for insufficient funds is incorrect.");

        softAssert.assertAll();

    }

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
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        confirmPage = new ConfirmPage(webDriver);
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
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    ConfirmPage confirmPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    int roomIndex;
}
