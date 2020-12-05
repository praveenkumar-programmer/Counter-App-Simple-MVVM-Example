package com.geeks4ever.counter_app.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.geeks4ever.counter_app.model.CountModel;

import java.util.concurrent.Executors;

@Database(entities = {CountModel.class}, version = 1, exportSchema = false)
public abstract class CountDatabase extends RoomDatabase {

    private static CountDatabase db;

    public abstract CountDao countDao();

    public static synchronized CountDatabase getInstance(Context context){

        if(db == null){
            db = Room.databaseBuilder(context.getApplicationContext(),
                    CountDatabase.class, "count_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

}
