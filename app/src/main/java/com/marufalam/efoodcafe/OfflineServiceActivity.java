package com.marufalam.efoodcafe;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineServiceActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
