package com.geeks4ever.counter_app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.geeks4ever.counter_app.model.CountModel;
import com.geeks4ever.counter_app.model.repository.CountRepository;

import java.util.Objects;

public class CounterViewModel extends AndroidViewModel {

    private final CountRepository countRepository;

    final MutableLiveData<String> count = new MutableLiveData<>();
    final LiveData<CountModel> data;

    public CounterViewModel(@NonNull Application application) {
        super(application);

        countRepository = CountRepository.getInstance();
        data = countRepository.getCount();
        data.observe(getApplication(), new Observer<CountModel>() {
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
}
