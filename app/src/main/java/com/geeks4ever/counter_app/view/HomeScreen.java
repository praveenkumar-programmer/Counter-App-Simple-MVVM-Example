package com.geeks4ever.counter_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.geeks4ever.counter_app.R;
import com.geeks4ever.counter_app.databinding.HomeScreenBinding;
import com.geeks4ever.counter_app.viewmodel.CounterViewModel;

public class HomeScreen extends AppCompatActivity implements Listener {

    private CounterViewModel viewModel;
    private LiveData<String> count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.home_screen);
        binding.setCount("0");

        binding.setListeners(this);

        viewModel = new CounterViewModel(getApplication());

        count = viewModel.getCount();

        count.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.setCount(s);
            }
        });

    }

    @Override
    public void onClick(View view) {
        viewModel.IncrementCount();
    }
}