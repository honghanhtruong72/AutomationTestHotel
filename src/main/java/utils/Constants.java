package utils;

import model.CreditCard;

public class Constants {
    public static final String HOTEL_BOOKING_URL = "http://14.176.232.213:8084/";
    public static final String YOPMAIL_URL = "https://yopmail.com/";
    public static final String ROOM_TYPE = "team03";
    public static final String FULL_NAME = "test 03";
    public static final String MAIL = "hotel@yopmail.com";
    public static final String PHONE_NUMBER = "012345678";
    public static final String ADDRESS = "92 Quang Trung, Danang";
    public static final String MESSAGE_BOOKING_SUCCESS = "Thank you! Your booking has been placed. We will contact you to confirm about the booking soon.";
    public static final String TITLE_MAIL_BOOKING_SUCCESS = "Your booking has been placed";
    public static final String ERROR_MESSAGE_BOOKING_NOT_FOUND = "Opps ! No booking found !";
    public static final String ERROR_MESSAGE_CARD_NOT_EXIST = "CreditCard not exists !!!";
    public static final String ERROR_MESSAGE_WRONG_CARD_INFO = "Wrong CreditCard information !!!";
    public static final String ERROR_MESSAGE_CARD_NOT_MONEY = "Balances not enough money !!!";
    public static final String USERNAME = "test 03";
    public static final String PASSWORD = "123456";
    public static final String VALID_PROMOCODE = "MT03";
    public static final String TITLE_MAIL_CANCEL_BOOKING = "Cancel booking !";
    public static final CreditCard VALID_CREDIT_CARD = new CreditCard(
            "2222 3333 4444 5555", "JOHN HENRY", "12/25", "123"
    );
    public static final CreditCard CREDIT_CARD_NO_MONEY = new CreditCard(
            "0000 4444 5555 6666", "MR SMITH", "12/25", "123"
    );


}
