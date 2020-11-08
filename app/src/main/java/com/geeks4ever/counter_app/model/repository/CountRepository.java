package com.geeks4ever.counter_app.model.repository;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.geeks4ever.counter_app.model.CountModel;

public class CountRepository {

    public static CountRepository repository;
    private final CountModel countModel;

    final MutableLiveData<CountModel> data = new MutableLiveData<>();

    private CountRepository(){
        countModel = new CountModel();
        countModel.setCount("0");
    }

    //Singleton Implementation
    public static CountRepository getInstance(){


        if(repository == null)
            repository = new CountRepository();
        return repository;
    }


    public LiveData<CountModel> getCount(){
        data.setValue(countModel);
        return data;
    }

    public void setCount(int count){

        countModel.setCount(String.valueOf(count));
        data.postValue(countModel);
    }

}
