package com.example.young.retrofit;

public class Category {
    int category_id;
    String category_title;
    String category_image_url;
    String category_detail_info;
    String beacon_number;
    String soundqr_number;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getCategory_image_url() {
        return category_image_url;
    }

    public void setCategory_image_url(String category_image_url) {
        this.category_image_url = category_image_url;
    }

    public String getCategory_detail_info() {
        return category_detail_info;
    }

    public void setCategory_detail_info(String category_detail_info) {
        this.category_detail_info = category_detail_info;
    }

    public String getBeacon_number() {
        return beacon_number;
    }

    public void setBeacon_number(String beacon_number) {
        this.beacon_number = beacon_number;
    }

    public String getSoundqr_number() {
        return soundqr_number;
    }

    public void setSoundqr_number(String soundqr_number) {
        this.soundqr_number = soundqr_number;
    }
}
