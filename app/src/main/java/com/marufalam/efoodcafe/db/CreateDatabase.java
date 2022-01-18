package com.marufalam.efoodcafe.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.marufalam.efoodcafe.daos.UserDao;
import com.marufalam.efoodcafe.models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//singleton partten

@Database(entities = {User.class}, version = 1)
public abstract class CreateDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();

    private static CreateDatabase db;

    //public static final ExecutorService service = Executors.newFixedThreadPool(4);

    public static CreateDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, CreateDatabase.class, "efoodcafe_db").allowMainThreadQueries().build();
            return db;
        }
        return db;
    }
}
