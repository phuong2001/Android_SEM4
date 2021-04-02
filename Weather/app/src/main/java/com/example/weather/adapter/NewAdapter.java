package com.example.weather.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weather.R;
import com.example.weather.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Item> weatherList;

    public NewAdapter(Activity activity, List<Item> weatherList) {
        this.activity = activity;
        this.weatherList = weatherList;
    }

    public void reloadData(List<Item> weatherList){
        this.weatherList = weatherList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View weatherView = activity.getLayoutInflater().inflate(R.layout.main_test, parent, false);
        NewHolder holder = new NewHolder(weatherView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewHolder hd = (NewHolder) holder;
        Item model = weatherList.get(position);
        hd.tvTime.setText(convertTime(model.getDateTime()));
        hd.tvTemperatures.setText(String.valueOf(model.getTemperature().getValue()));

        if (model.getWeatherIcon() < 10) {
            Glide.with(activity).load("https://developer.accuweather.com/sites/default/files/0" + model.getWeatherIcon() + "-s.png").into(hd.tvIcon);
        }else {
            Glide.with(activity).load("https://developer.accuweather.com/sites/default/files/" + model.getWeatherIcon() + "-s.png").into(hd.tvIcon);
        }

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public  class NewHolder extends RecyclerView.ViewHolder{
        TextView tvTime, tvTemperatures;
        ImageView tvIcon;


        public NewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTemperatures = itemView.findViewById(R.id.tvTemperatures);
            tvIcon = itemView.findViewById(R.id.tvIcon);
        }
    }

    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }

}
