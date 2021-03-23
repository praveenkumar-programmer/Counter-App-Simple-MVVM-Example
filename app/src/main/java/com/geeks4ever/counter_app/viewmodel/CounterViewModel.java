package com.geeks4ever.counter_app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.geeks4ever.counter_app.model.CountModel;
import com.geeks4ever.counter_app.model.repository.CountRepository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterViewModel extends AndroidViewModel {

    private final CountRepository countRepository;

    ExecutorService executorService;

    final MutableLiveData<String> count = new MutableLiveData<>();

    public CounterViewModel(@NonNull Application application) {
        super(application);

        count.setValue("0");

        countRepository = new CountRepository(application);
        countRepository.getCount().observeForever( new Observer<List<CountModel>>() {
            @Override
            public void onChanged(List<CountModel> countModel) {
                if(!countModel.isEmpty())
                    count.setValue(countModel.get(0).getCount());
            }
        });
    }


    public LiveData<String> getCount(){
        return count;
    }

    public void IncrementCount(){
        int countint = Integer.parseInt(Objects.requireNonNull(count.getValue()));
        countRepository.setCount(countint+1);
    }

    public int DecrementCount(){
        int countint = Integer.parseInt(Objects.requireNonNull(count.getValue()));
        if (countint > 0){
            countint--;
            countRepository.setCount(countint);
        }
        return countint;
    }

    public void MeltDown(){

        if(executorService == null) {

            executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (DecrementCount() > 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    executorService = null;
                }
            });

        }

    }
}
