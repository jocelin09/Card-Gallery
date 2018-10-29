package com.cardmgnt.jocelinthomas.carddetails.Pan;

/**
 * Created by jocelinthomas on 02/05/18.
 */

public class PanModel {
    public int panid;
    public String pan_name;
    public String pan_number;

    public String getPan_name() {
        return pan_name;
    }

    public void setPan_name(String pan_name) {
        this.pan_name = pan_name;
    }

    public String getPan_number() {
        return pan_number;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }

    public int getPanid() {
        return panid;
    }

    public void setPanid(int panid) {
        this.panid = panid;
    }
}
