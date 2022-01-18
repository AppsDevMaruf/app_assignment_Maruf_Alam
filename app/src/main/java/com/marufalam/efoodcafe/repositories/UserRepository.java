package com.marufalam.efoodcafe.repositories;

import android.content.Context;

import com.marufalam.efoodcafe.daos.UserDao;
import com.marufalam.efoodcafe.db.CreateDatabase;
import com.marufalam.efoodcafe.models.User;

public class UserRepository {
private final UserDao userDao;

    public UserRepository(Context context) {
        userDao = CreateDatabase.getInstance(context).getUserDao();

    }
    public void createUser(User user){
        userDao.createUser(user);
    }
public User authUser(String email,String password,String userRole){
        return userDao.authUser(email,password,userRole);

}
    /*public User authUser(String email, String password, String userRole){
        return UserDao.authUser(email, password, userRole);
    }*/
}
