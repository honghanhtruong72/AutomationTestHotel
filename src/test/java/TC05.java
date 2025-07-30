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

public class TC05 {
    @Test(
            description = " Verify auto-filled fields on 'Add your information' form on Book Now Page"
    )
    public void VerifyAutoFilledFieldsOnAddYourInformationFormOnBookNowPage() {

        homePage.openMyAccount();

        expectedFullName = myAccountPage.getFullNameTextBoxValue();
        expectedEmail = myAccountPage.getEmailTextBoxValue();
        expectedPhone = myAccountPage.getPhoneNumberTextBoxValue();
        expectedAddress = myAccountPage.getAdressTextBoxValue();

        homePage.openRoomsPage();

        checkInDate = LocalDate.now().plusWeeks(1);

        checkOutDate = checkInDate.plusDays(2);

        roomsPage.openRoomDetailByRoomType(Constants.ROOM_TYPE);
        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        actualFullName = bookNowPage.getFullNameTextBoxValue();
        actualEmail = bookNowPage.getEmailTextBoxValue();
        actualPhone = bookNowPage.getPhoneTextBoxValue();
        actualAddress = bookNowPage.getAddressTextBoxValue();

        softAssert.assertEquals(actualFullName, expectedFullName, "Full name not match");
        softAssert.assertEquals(actualEmail, expectedEmail, "Email not match");
        softAssert.assertEquals(actualPhone, expectedPhone, "PhoneNumber not match");
        softAssert.assertEquals(actualAddress, expectedAddress, "Address not match");


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
        roomDetailsPage = new RoomDetailsPage(webDriver);
        bookNowPage = new BookNowPage(webDriver);
        checkoutPage = new CheckoutPage(webDriver);
        myAccountPage = new MyAccountPage(webDriver);

        homePage.login(Constants.USERNAME, Constants.PASSWORD);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    int roomIndex;
    String expectedFullName;
    String expectedEmail;
    String expectedPhone;
    String expectedAddress;
    String actualFullName;
    String actualEmail;
    String actualPhone;
    String actualAddress;

    WebDriver webDriver;
    SoftAssert softAssert;
    HomePage homePage;
    RoomsPage roomsPage;
    RoomDetailsPage roomDetailsPage;
    BookNowPage bookNowPage;
    CheckoutPage checkoutPage;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    MyAccountPage myAccountPage;
}
