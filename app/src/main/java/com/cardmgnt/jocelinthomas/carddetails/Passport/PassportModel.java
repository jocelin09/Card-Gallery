package com.cardmgnt.jocelinthomas.carddetails.Passport;

/**
 * Created by jocelinthomas on 05/05/18.
 */

public class PassportModel {
    public int passport_id;
    public String passport_name;
    public String passport_number;


    public String getPassport_name() {
        return passport_name;
    }

    public void setPassport_name(String passport_name) {
        this.passport_name = passport_name;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public int getPassport_id() {
        return passport_id;
    }

    public void setPassport_id(int passport_id) {
        this.passport_id = passport_id;
    }
}
