package com.cardmgnt.jocelinthomas.carddetails.CreditCard;

/**
 * Created by jocelinthomas on 08/09/18.
 */

public class CreditCardModel {
    public int creditcardid;
    public String creditcard_name;
    public String creditcard_number;

    public int getCreditcardid() {
        return creditcardid;
    }

    public void setCreditcardid(int creditcardid) {
        this.creditcardid = creditcardid;
    }

    public String getcreditcard_name() {
        return creditcard_name;
    }

    public void setcreditcard_name(String creditcard_name) {
        this.creditcard_name = creditcard_name;
    }

    public String getcreditcard_number() {
        return creditcard_number;
    }

    public void setcreditcard_number(String creditcard_number) {
        this.creditcard_number = creditcard_number;
    }


}
