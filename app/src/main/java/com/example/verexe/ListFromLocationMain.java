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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.verexe.adapter.AdapterListFromLocation;
import com.example.verexe.adapter.AdapterListTrip;
import com.example.verexe.adapter.MyAdapter;
import com.example.verexe.model.Coach;
import com.example.verexe.model.Intersection;
import com.example.verexe.model.Seat;
import com.example.verexe.model.Trip;
import com.example.verexe.model.TypeSort;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListFromLocationMain extends AppCompatActivity {

    private ArrayList<Intersection> intersectionArrayList;

    RecyclerView rclView;

    AdapterListFromLocation adapterListFromLocation;

    private Handler mHandler;

    private String valueIdTrip;
    static ArrayList<Seat> seatListOrder = new ArrayList<>();

    private ImageButton imgBackFromLocation;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_from_location_main);
        rclView = findViewById(R.id.rclViewFromLocation);
        imgBackFromLocation = findViewById(R.id.imgBackFromLocation);
        getSupportActionBar().hide();
        mHandler = new Handler(Looper.getMainLooper());

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("add");
        valueIdTrip = bundle.getString("id");
        seatListOrder = bundle.getParcelableArrayList("seat_order");
        adapterListFromLocation = new AdapterListFromLocation(this,new ArrayList<>(),ListFromLocationMain.this,seatListOrder,valueIdTrip);
        rclView.setAdapter(adapterListFromLocation);
        rclView.setLayoutManager(new LinearLayoutManager(this));
        filter(UUID.fromString(valueIdTrip));

        imgBackFromLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ListFromLocationMain.this, ListSeat.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", valueIdTrip);
                intent1.putExtra("add", bundle);
                finish();
                startActivity(intent1);
            }
        });
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
                                adapterListFromLocation = new AdapterListFromLocation(ListFromLocationMain.this, listArray.get(0).getFromLocation(),ListFromLocationMain.this,seatListOrder,valueIdTrip);
                                rclView.setAdapter(adapterListFromLocation);
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