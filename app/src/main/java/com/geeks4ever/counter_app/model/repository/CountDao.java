package com.geeks4ever.counter_app.model.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.geeks4ever.counter_app.model.CountModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CountDao {

    @Query("Select * from countmodel where `key`= 0")
    LiveData<List<CountModel>> getCount();

    @Insert(onConflict = REPLACE)
    void setCount(CountModel count);

}
