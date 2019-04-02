package com.mybaitspuls.demo.entity;

public class DynamicUserPicturePo extends Dynamic{
    private User user;
    private Picture picture;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "DynamicUserPicturePo{" +
                "user=" + user +
                ", picture=" + picture +
                '}'+"super:"+super.toString();
    }
}
