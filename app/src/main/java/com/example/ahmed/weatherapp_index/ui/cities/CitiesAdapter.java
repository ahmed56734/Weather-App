package com.example.ahmed.weatherapp_index.ui.cities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.weatherapp_index.R;
import com.example.ahmed.weatherapp_index.data.models.UserCity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 2/10/18.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityItemViewHolder> {

    private List<UserCity> cities;
    private CityOnClickListener cityOnClickListener;


    CitiesAdapter(CityOnClickListener cityOnClickListener) {
        cities = new ArrayList<>();
        this.cityOnClickListener = cityOnClickListener;
    }

    public void addCity(UserCity city) {
        cities.add(city);
        notifyItemInserted(cities.size()-1);

    }

    public void updateData(List<UserCity> cities) {
        this.cities.clear();
        this.cities.addAll(cities);

        notifyDataSetChanged();
    }

    public UserCity getCity(int position) {
        return cities.get(position);
    }

    public void deleteCity(int position) {
        cities.remove(position);
        notifyItemRemoved(position);
    }


    public void addCities(List<UserCity> cities) {
        this.cities.addAll(cities);
    }


    @Override
    public CityItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        return new CityItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityItemViewHolder holder, int position) {

        UserCity city = cities.get(position);
        holder.bind(city);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }



    class CityItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityNameTextView;

        CityItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cityNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserCity city = cities.get(getAdapterPosition());
                    cityOnClickListener.onClick(city);
                }
            });
        }

        void bind(UserCity city) {
            cityNameTextView.setText(city.getName());
        }


    }

    interface CityOnClickListener {
        void onClick(UserCity city);
    }
}
