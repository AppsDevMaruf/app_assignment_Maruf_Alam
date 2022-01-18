package com.marufalam.efoodcafe.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.marufalam.efoodcafe.models.User;
import com.marufalam.efoodcafe.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);

    }
    public  void  crateUser(User user){
        userRepository.createUser(user);
    }
    public User authUser(String email,String password ,String userRole){
       return userRepository.authUser(email,password,userRole);
    }
}
