package com.geeks4ever.counter_app.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.geeks4ever.counter_app.model.CountModel;

public class CountRepository {

    public static CountRepository repository;
    private CountModel countModel;

    final MutableLiveData<CountModel> data = new MutableLiveData<>();

    private CountRepository(){
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
