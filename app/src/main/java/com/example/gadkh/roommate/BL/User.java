package com.example.gadkh.roommate.BL;

public class User {
    private String fullName;
    private String user_id;
    private String gender;

    public User() {
    }

    public User(String fullName, String user_id, String gender) {
        this.fullName = fullName;
        this.user_id = user_id;
        this.gender = gender;

    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", user_id='" + user_id + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}