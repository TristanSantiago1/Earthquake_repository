package com.example.desapp008;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.desapp008.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EarthquakeAdapter earthquakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rcwEarthquakesList.setLayoutManager(new LinearLayoutManager(this));
        earthquakeAdapter = new EarthquakeAdapter();
        binding.rcwEarthquakesList.setAdapter(earthquakeAdapter);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        earthquakeAdapter.submitList(viewModel.geteqList().getValue());

        viewModel.geteqList().observe(this, eqList->{
           for (Earthquake eq : eqList){
               earthquakeAdapter.submitList(viewModel.geteqList().getValue());
               Log.d("eq",eq.getMagnitude()+" "+eq.getPlace());
           }
        });
        viewModel.getEaryquake();

    }
}