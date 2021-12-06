package com.example.networking_week11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class dbCitiesAdapter extends RecyclerView.Adapter<dbCitiesAdapter.CitiesViewHolder> {

        interface cityClickListner {
            public void cityClicked(City selectedCity);
        }
        private Context mCtx;
        public List<City> cityList;
        cityClickListner listner;

        public dbCitiesAdapter(Context mCtx, List<City> cityList) {
            this.mCtx = mCtx;
            this.cityList = cityList;
            listner = (cityClickListner)mCtx;
        }

        @Override
        public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_cities, parent, false);
            return new CitiesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CitiesViewHolder holder, int position) {
            City t = cityList.get(position);
            holder.cityTextView.setText(t.getCityName() );
        }

        @Override
        public int getItemCount() {
            return cityList.size();
        }
        class CitiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView cityTextView, countryTextView;
            public CitiesViewHolder(View itemView) {
                super(itemView);
                cityTextView = itemView.findViewById(R.id.cityy);
                itemView.setOnClickListener(this);
            }
            @Override
            public void onClick(View view) {
                City city = cityList.get(getAdapterPosition());
                listner.cityClicked(city);
            }
        }
    }
