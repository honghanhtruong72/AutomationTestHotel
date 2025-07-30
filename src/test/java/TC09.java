import io.qameta.allure.Step;
import model.CreditCard;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.hotel.*;
import utils.Constants;

import java.time.LocalDate;
import java.util.Random;

public class TC09 {
    @Test(
            description = "Verify that the system validates card numbers with insufficient digits"
    )
    public void VerifySystemValidatesCardNumbersWithInsufficientDigits() {

        homePage.openRoomsPage();

        checkInDate = LocalDate.now().plusDays(3);
        checkOutDate = checkInDate.plusDays(1);

        roomsPage.openRoomDetailByRoomType(Constants.ROOM_TYPE);
        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);
        bookNowPage.submitUserInfoForm(Constants.FULL_NAME,
                Constants.MAIL, Constants.PHONE_NUMBER, Constants.ADDRESS);

        invalidNumber = faker.number().digits(12);
        CreditCard invalidCard = Constants.VALID_CREDIT_CARD.cloneCard();
        invalidCard.setCardNumber(invalidNumber);

        checkoutPage.submitCardDetails(invalidCard);

        softAssert.assertEquals(checkoutPage.getErrorMessageForCreditCard(), Constants.ERROR_MESSAGE_CARD_NOT_EXIST,
                "Error message for invalid card number is incorrect");

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
        faker = new Faker();
    }

//    @AfterMethod
//    public void tearDown() {
//        webDriver.quit();
//    }

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
    Faker faker;
    String invalidNumber;
}
