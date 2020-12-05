package com.geeks4ever.counter_app.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countmodel")
public class CountModel {

    @PrimaryKey
    public int key;

    @ColumnInfo(name = "count")
    private String count;

    public CountModel(int key, String count){
        this.key = key;
        this.count = count;
    }

    public String getCount(){
        Log.e("db", "getCount"+this.count);
        return this.count;
    }

    public void setCount(String count){

        Log.e("db", "setCount"+count);
        this.count = count;}
}
