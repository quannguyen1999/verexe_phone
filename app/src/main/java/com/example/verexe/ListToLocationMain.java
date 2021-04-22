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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.verexe.adapter.AdapterListFromLocation;
import com.example.verexe.adapter.AdapterListToLocation;
import com.example.verexe.model.Intersection;
import com.example.verexe.model.Location;
import com.example.verexe.model.Seat;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListToLocationMain extends AppCompatActivity {

    private ArrayList<Intersection> intersectionArrayList;

    RecyclerView rclView;

    AdapterListToLocation adapterListToLocation;

    private Handler mHandler;

    private String valueIdTrip;
    private Location fromLocation;
    private ImageView imgNumber;
    private TextView txtToLocation;
    static ArrayList<Seat> seatListOrder = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_location_main);

        rclView = findViewById(R.id.rclViewToLocation);
        getSupportActionBar().hide();
        mHandler = new Handler(Looper.getMainLooper());

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("add");
        valueIdTrip = bundle.getString("id");
        seatListOrder = bundle.getParcelableArrayList("seat_order");
        fromLocation = bundle.getParcelable("fromLocation");
        adapterListToLocation = new AdapterListToLocation(this,new ArrayList<>(),ListToLocationMain.this,seatListOrder,valueIdTrip,fromLocation);
        rclView.setAdapter(adapterListToLocation);
        rclView.setLayoutManager(new LinearLayoutManager(this));
        filter(UUID.fromString(valueIdTrip));
    }

    List<Intersection> listArray;
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Intersection> filter(UUID id) {
        listArray = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        String url = HttpRequestCommon.url_intersection_id+id;
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    try {
                        JSONObject json= new JSONObject(myResponse);  //your response
                        JSONObject objectTripResponse = json.getJSONObject("data");
                        JSONObject jsonObject = objectTripResponse;
                        Gson gson = new Gson();
                        Intersection intersection = gson.fromJson(String.valueOf(jsonObject),Intersection.class);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listArray.add(intersection);
                                adapterListToLocation = new AdapterListToLocation(ListToLocationMain.this,
                                        listArray.get(0).getToLocation(),
                                        ListToLocationMain.this,
                                        seatListOrder,valueIdTrip,fromLocation);
                                rclView.setAdapter(adapterListToLocation);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return listArray;
    }
}