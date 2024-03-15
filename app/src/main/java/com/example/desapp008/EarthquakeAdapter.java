package com.example.desapp008;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desapp008.databinding.EarthquakeCardBinding;

public class EarthquakeAdapter extends ListAdapter<Earthquake, EarthquakeAdapter.EarthquakeViewHolder> {

    public static final DiffUtil.ItemCallback<Earthquake> DIFFER_CALLBACK = new DiffUtil.ItemCallback<Earthquake>() {
        @Override
        public boolean areItemsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    };


    protected EarthquakeAdapter() {super(DIFFER_CALLBACK);}

    @NonNull
    @Override
    public EarthquakeAdapter.EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EarthquakeCardBinding binding = EarthquakeCardBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new EarthquakeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeAdapter.EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bindEarthquake(earthquake, position);
    }

    public class EarthquakeViewHolder extends RecyclerView.ViewHolder {

        private EarthquakeCardBinding BINDING;
        public EarthquakeViewHolder(@NonNull EarthquakeCardBinding binding) {
            super(binding.getRoot());
            this.BINDING = binding;
        }

        public void bindEarthquake(Earthquake earthquake, int position){
            BINDING.place.setText(earthquake.getPlace());
            BINDING.longitude.setText(String.valueOf(earthquake.getLongitude()));
            BINDING.time.setText(String.valueOf(earthquake.getTime()));
            BINDING.magnitude.setText(String.valueOf(earthquake.getMagnitude()));
            BINDING.executePendingBindings();
            BINDING.number.setText(String.valueOf(position));
        }
    }
}
