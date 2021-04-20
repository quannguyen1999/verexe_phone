package com.example.verexe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;

import com.example.verexe.adapter.AdapterListTrip;
import com.example.verexe.adapter.MyAdapter;
import com.example.verexe.model.Trip;
import com.example.verexe.service.ProvinceService;
import com.example.verexe.service.TripService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListTrip extends AppCompatActivity {
    ImageButton imgBackXP;
    AdapterListTrip adapterListTrip;
    RecyclerView rclView;
    private Handler mHandler;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);
        getSupportActionBar().hide();
        metaData();
        mHandler = new Handler(Looper.getMainLooper());
        adapterListTrip = new AdapterListTrip(ListTrip.this, filter("a","b",LocalDate.now()),ListTrip.this);
        handle();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("search");
        String from = bundle.getString("from");
        String to = bundle.getString("to");
        LocalDate dateDepart = (LocalDate) bundle.get("date");
        filter(from,to,dateDepart);
    }

    private void metaData(){
        rclView = findViewById(R.id.rclViewListTrip);
        imgBackXP = findViewById(R.id.imgBackXP);
    }

    private void handle(){
        imgBackXP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    List<Trip> listArray;
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Trip> filter(String from, String to, LocalDate dateDepart) {
        listArray = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        String url = HttpRequestCommon.url_trip_search;
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("fromLocation", from);
            postdata.put("toLocation", to);
            postdata.put("dateDepart", dateDepart.toString());
        } catch(JSONException e){
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
        Request request = new Request.Builder()
                .url(HttpRequestCommon.url_trip_search)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String myResponse = response.body().string();
                    try {
                        JSONObject json= new JSONObject(myResponse);  //your response
                        JSONObject objectTripResponse = json.getJSONObject("data");
                        JSONArray result = objectTripResponse.getJSONArray("tripResponseList");
                        for(int i=0;i<result.length();i++){
                            JSONObject jsonObject = result.getJSONObject(i);
                            Gson gson = new Gson();
                            Trip trip = gson.fromJson(String.valueOf(jsonObject),Trip.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listArray.add(trip);
                                    adapterListTrip = new AdapterListTrip(ListTrip.this, listArray,ListTrip.this);
                                    rclView.setAdapter(adapterListTrip);
                                    rclView.setLayoutManager(new LinearLayoutManager(ListTrip.this));
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return listArray;
    }
}