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

public class TC05 {
    @Test(
            description = " Verify auto-filled fields on 'Add your information' form on Book Now Page"
    )
    public void VerifyAutoFilledFieldsOnAddYourInformationFormOnBookNowPage() {

        header.login(Constants.USERNAME, Constants.PASSWORD);

        header.openMyAccount();

        expectedFullname = myAccountPage.getFullNameTextBoxValue();
        expectedEmail = myAccountPage.getEmailTextBoxValue();
        expectedPhone = myAccountPage.getPhoneNumberTextBoxValue();
        expectedAdress = myAccountPage.getAdressTextBoxValue();

        header.openRoomsPage();

        roomIndex = random.nextInt(roomsPage.getTotalRooms());

        checkInDate = LocalDate.now().plusMonths(2);

        checkOutDate = checkInDate.plusDays(1);

        roomsPage.openRoomDetailByIndex(roomIndex);

        roomDetailsPage.submitBookingForm(checkInDate, checkOutDate, 1, 0);

        actualFullname = bookNowPage.getFullNameTextBoxValue();
        actualEmail = bookNowPage.getEmailTextBoxValue();
        actualPhone = bookNowPage.getPhoneTextBoxValue();
        actualAdress = bookNowPage.getAdressTextBoxValue();

        softAssert.assertEquals(actualFullname, expectedFullname, "Full name not match");
        softAssert.assertEquals(actualEmail, expectedEmail, "Email not match");
        softAssert.assertEquals(actualPhone, expectedPhone, "PhoneNumber not match");
        softAssert.assertEquals(actualAdress, expectedAdress, "Adress not match");


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
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

    int roomIndex;
    String expectedFullname;
    String expectedEmail;
    String expectedPhone;
    String expectedAdress;
    String actualFullname;
    String actualEmail;
    String actualPhone;
    String actualAdress;

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
}
