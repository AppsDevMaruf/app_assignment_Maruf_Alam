package com.marufalam.efoodcafe.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.marufalam.efoodcafe.models.User;

@Dao
public interface UserDao {


    @Insert
    void createUser(User user);
    @Query("SELECT * FROM users where email= :mail and password= :password")
    User getUser(String mail, String password);

    @Query("SELECT * FROM users where email = :email AND password= :password AND userRole = :userRole")
    User authUser(String email, String password, String userRole);

}
