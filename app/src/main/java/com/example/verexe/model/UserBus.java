package com.example.verexe.model;

import java.util.Date;
import java.util.UUID;

public class UserBus {
    private UUID _id;

    private String phone;


    private String email;


    private String password;

    private String fullName;

    private UserType userType;


    private Date createAt;

    public UserBus() {
    }

    public UserBus(UUID _id, String phone, String email, String password, String fullName, UserType userType, Date createAt) {
        this._id = _id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.userType = userType;
        this.createAt = createAt;
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "UserBus{" +
                "_id=" + _id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userType=" + userType +
                ", createAt=" + createAt +
                '}';
    }
}
