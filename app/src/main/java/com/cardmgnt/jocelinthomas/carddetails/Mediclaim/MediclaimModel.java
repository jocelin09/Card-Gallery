package com.cardmgnt.jocelinthomas.carddetails.Mediclaim;

/**
 * Created by jocelinthomas on 02/05/18.
 */

public class MediclaimModel {
    public int mediclaimid;
    public String mediclaim_name;
    public String mediclaim_number;

    public String getmediclaim_name() {
        return mediclaim_name;
    }

    public void setmediclaim_name(String mediclaim_name) {
        this.mediclaim_name = mediclaim_name;
    }

    public String getmediclaim_number() {
        return mediclaim_number;
    }

    public void setmediclaim_number(String mediclaim_number) {
        this.mediclaim_number = mediclaim_number;
    }

    public int getmediclaimid() {
        return mediclaimid;
    }

    public void setmediclaimid(int mediclaimid) {
        this.mediclaimid = mediclaimid;
    }
}
