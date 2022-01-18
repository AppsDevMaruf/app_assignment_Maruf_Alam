package com.marufalam.efoodcafe.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String name;
    private String mobileNumber;
    private String password;
    private String userRole;

    public User(String email, String name, String mobileNumber, String password, String userRole) {
        this.email = email;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.userRole = userRole;
    }
    @Ignore
    public User(int id, String email, String name, String mobileNumber, String password, String userRole) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.userRole = userRole;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
