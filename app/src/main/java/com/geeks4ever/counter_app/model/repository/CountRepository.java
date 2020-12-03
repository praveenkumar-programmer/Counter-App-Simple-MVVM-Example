package com.geeks4ever.counter_app.model.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.geeks4ever.counter_app.model.CountModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountRepository {

    private CountDao countDao;
    private LiveData<List<CountModel>> countModelLiveData;

    public CountRepository(Application application){

        countDao = CountDatabase.getInstance(application).countDao();
        countModelLiveData = countDao.getCount();

    }

    public LiveData<List<CountModel>> getCount(){
        return countModelLiveData;
    }

    public void setCount(int count){
        new SetCountAsyncTask(countDao).execute(new CountModel(0, String.valueOf(count)));
    }

    private static class SetCountAsyncTask extends AsyncTask<CountModel, Void, Void>{

        private CountDao countDao;

        private SetCountAsyncTask(CountDao countDao){
            this.countDao = countDao;
        }

        @Override
        protected Void doInBackground(CountModel... countModels) {
            countDao.setCount(countModels[0]);
            return null;
        }
    }

}
