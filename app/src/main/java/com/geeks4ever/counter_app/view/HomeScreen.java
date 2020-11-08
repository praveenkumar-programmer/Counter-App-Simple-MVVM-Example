package com.geeks4ever.counter_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.geeks4ever.counter_app.R;
import com.geeks4ever.counter_app.databinding.HomeScreenBinding;
import com.geeks4ever.counter_app.viewmodel.CounterViewModel;
import com.geeks4ever.counter_app.viewmodel.viewModelFactory;

public class HomeScreen extends AppCompatActivity implements Listener {

    private CounterViewModel viewModel;
    private LiveData<String> count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.home_screen);
        binding.setCount("0");

        binding.setListeners(this);

        viewModel = new ViewModelProvider(this, new viewModelFactory()).get(CounterViewModel.class);

        count = viewModel.getCount();

        count.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.setCount(s);
            }
        });

    }

    @Override
    public void Increment(View view) {
        viewModel.IncrementCount();
    }

    @Override
    public void MeltDown(View view) {
        viewModel.MeltDown();
    }
}