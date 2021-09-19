package com.daemon.fiancy.models;

public class UserModel {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean uPremium = false;

    public UserModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isuPremium() {
        return uPremium;
    }

    public void setuPremium(boolean uPremium) {
        this.uPremium = uPremium;
    }
}
