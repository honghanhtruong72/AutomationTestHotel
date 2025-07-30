package model;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CreditCard {
    String cardNumber;
    String cardName;
    YearMonth expiryDate;
    String cvv;

    public CreditCard(String cardNumber, String cardName, String expiryDateString, String cvv) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        this.expiryDate = YearMonth.parse(expiryDateString, formatter);
        this.cvv = cvv;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpiryDateString() {
        return expiryDate.format(DateTimeFormatter.ofPattern("MM/yy"));
    }

    public CreditCard cloneCard() {
        return new CreditCard(cardNumber, cardName, getExpiryDateString(), cvv);
    }
}
