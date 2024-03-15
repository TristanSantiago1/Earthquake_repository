package com.example.desapp008;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.desapp008.api.ApiClient;
import com.example.desapp008.api.EarthquakeJSONResponse;
import com.example.desapp008.api.Feature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Earthquake>> eqList = new MutableLiveData<>();
    public LiveData<List<Earthquake>> geteqList(){

        return eqList;
    }

    private MainRepository repository = new MainRepository();

    public void getEaryquake(){
        repository.getEarthquake(earquakeList ->{
            eqList.setValue(earquakeList);
        });
    }




}
