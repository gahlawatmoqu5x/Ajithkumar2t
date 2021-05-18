package com.aiyolabs.hashchat.model;

public class SMS {
    public String message,address,date;

    public String getMessage() {
        return message;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
