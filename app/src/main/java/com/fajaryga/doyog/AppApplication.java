package com.fajaryga.doyog;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.fajaryga.doyog.room.AppDatabase;

public class AppApplication extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"wisata").allowMainThreadQueries().build();
    }

}
