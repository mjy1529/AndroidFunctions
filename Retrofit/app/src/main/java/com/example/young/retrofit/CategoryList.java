package com.example.young.retrofit;

import java.util.ArrayList;

public class CategoryList {

    ArrayList<Category> category_info = new ArrayList<>();

    public CategoryList() {

    }

    public CategoryList(ArrayList<Category> categoryList) {
        this.category_info = categoryList;
    }

    public ArrayList<Category> getCategory_info() {
        return category_info;
    }

    public void setCategory_info(ArrayList<Category> category_info) {
        this.category_info = category_info;
    }
}
