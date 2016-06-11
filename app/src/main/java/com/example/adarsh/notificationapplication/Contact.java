package com.example.adarsh.notificationapplication;

/**
 * Created by adarsh on 11/6/16.
 */
public class Contact {
    private String name, phone, category;

    public Contact() {
    }

    public Contact(String name, String phone, String category) {
        this.name = name;
        this.phone = phone;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String Description) {
        this.phone = Description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String Description) {
        this.category = Description;
    }

}