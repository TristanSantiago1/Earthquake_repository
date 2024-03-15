package com.example.desapp008;

import com.example.desapp008.api.ApiClient;
import com.example.desapp008.api.EarthquakeJSONResponse;
import com.example.desapp008.api.Feature;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public interface DowloadEqsLisneter{
        void onEqsDowloaded(List<Earthquake> eqList);
    }
    private List<Earthquake> getEarthquakeWithMoshi(EarthquakeJSONResponse body){
        ArrayList<Earthquake> eqList = new ArrayList<>();
        List<Feature> features = body.getFeatures();
        for(Feature feature : features){
            String id = feature.getId();
            double magnitude = feature.getProperties().getMagnitude();
            String place = feature.getProperties().getPlace();
            long time = feature.getProperties().getTime();

            double longitude = feature.getGeometry().getLingitude();
            double latitude = feature.getGeometry().getLatitude();

            Earthquake earthquake = new Earthquake(id, place, magnitude, time, 1.1, 1.1);
            eqList.add(earthquake);

        }
        return eqList;
    }

    public void getEarthquake(DowloadEqsLisneter dowloadEqsLisneter){
        ApiClient.Service service  = ApiClient.getInstance().getService();

        service.getEarthquakes().enqueue(new Callback<EarthquakeJSONResponse>() {//Enqueque es manera asincrona
            @Override
            public void onResponse(Call<EarthquakeJSONResponse> call, Response<EarthquakeJSONResponse> response) {
                List<Earthquake> eql = getEarthquakeWithMoshi(response.body());
                dowloadEqsLisneter.onEqsDowloaded(eql);
            }

            @Override
            public void onFailure(Call<EarthquakeJSONResponse> call, Throwable t) {

            }
        });

    }

}
