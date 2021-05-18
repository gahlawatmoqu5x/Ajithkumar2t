package com.aiyolabs.hashchat.model;

public class MessagePojo {
    public String number,message,animation;

    public String getMessage() {
        return message;
    }

    public String getNumber() {
        return number;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }
}
