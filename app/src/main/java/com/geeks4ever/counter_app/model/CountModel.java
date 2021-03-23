package com.geeks4ever.counter_app.model;

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
        return this.count;
    }
}
