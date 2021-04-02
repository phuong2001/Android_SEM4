package com.example.weather;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.adapter.NewAdapter;
import com.example.weather.model.Item;
import com.example.weather.network.APIManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Item> listData;
    NewAdapter adaper;
    TextView tvPhrase, tvTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        getListData();

        tvPhrase =findViewById(R.id.tvPhrase);
        tvTemperature = findViewById(R.id.tvTemperature);
        listData = new ArrayList<>();
        adaper = new NewAdapter(NewActivity.this, listData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        recyclerView = findViewById(R.id.rvNews);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaper);


    }

    private void getListData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);
        service.getWeatherData().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.body() !=null){
                    listData = response.body();
                    adaper.reloadData(listData);

                    tvPhrase.setText(listData.get(0).getIconPhrase());
                    tvTemperature.setText(String.valueOf(listData.get(0).getTemperature().getValue()));
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(NewActivity.this, "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }

}
