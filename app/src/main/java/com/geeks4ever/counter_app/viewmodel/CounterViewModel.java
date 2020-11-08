package com.geeks4ever.counter_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.geeks4ever.counter_app.model.CountModel;
import com.geeks4ever.counter_app.model.repository.CountRepository;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterViewModel extends ViewModel {

    private final CountRepository countRepository;

    ExecutorService executorService;

    final MutableLiveData<String> count = new MutableLiveData<>();
    final LiveData<CountModel> data;

    public CounterViewModel() {
        countRepository = CountRepository.getInstance();
        data = countRepository.getCount();
        data.observeForever( new Observer<CountModel>() {
            @Override
            public void onChanged(CountModel countModel) {
                count.setValue(countModel.getCount());
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
        countint--;
        countRepository.setCount(countint);
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
