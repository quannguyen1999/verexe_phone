package com.example.verexe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.verexe.adapter.AdapterListTrip;
import com.example.verexe.adapter.MyAdapter;
import com.example.verexe.model.Trip;
import com.example.verexe.model.TypeSort;
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
    TextView txtTitleLocation,txtTitleDateDepart;
    ImageButton imgBackXP;
    AdapterListTrip adapterListTrip;
    RecyclerView rclView;
    private Handler mHandler;

    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;
    private Button btnPriceLowToHight, btnPriceHightToLow, btnTimeLowToHight, btnTimeHightToLow, btnDeclineSort;
    private Button btnSort;

    private String from;
    private String to;
    private LocalDate dateDepart;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);
        getSupportActionBar().hide();
        metaData();
        mHandler = new Handler(Looper.getMainLooper());
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("search");
        from = bundle.getString("from");
        to = bundle.getString("to");
        dateDepart = (LocalDate) bundle.get("date");
        txtTitleLocation.setText(from+"-"+to);
        txtTitleDateDepart.setText(dateDepart.toString());
        adapterListTrip = new AdapterListTrip(ListTrip.this, filter(from,to,dateDepart,TypeSort.NONE),ListTrip.this);
        handle();
//        filter(from,to,dateDepart,TypeSort.NONE);
    }

    private void metaData(){
        rclView = findViewById(R.id.rclViewListTrip);
        imgBackXP = findViewById(R.id.imgBackXP);
        txtTitleLocation = findViewById(R.id.txtTitleLocation);
        txtTitleDateDepart = findViewById(R.id.txtTitleDateDepart);
        btnSort = findViewById(R.id.btnSort);
    }

    private void handle(){
        imgBackXP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseSort();
            }
        });
    }

    List<Trip> listArray;
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Trip> filter(String from, String to, LocalDate dateDepart, TypeSort typeSort) {
        listArray = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        String url = HttpRequestCommon.url_trip_search;
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("fromLocation", from);
            postdata.put("toLocation", to);
            postdata.put("dateDepart", dateDepart.toString());
            postdata.put("typeSort",typeSort.toString());
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

    public void chooseSort(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View formSort = getLayoutInflater().inflate(R.layout.form_sort,null);
        btnPriceHightToLow = (Button) formSort.findViewById(R.id.btnPriceHightToLow);
        btnPriceLowToHight = (Button) formSort.findViewById(R.id.btnPriceLowToHight);
        btnTimeHightToLow = (Button) formSort.findViewById(R.id.btnTimeHightToLow);
        btnTimeLowToHight = (Button) formSort.findViewById(R.id.btnTimeLowToHight);
        btnDeclineSort = (Button) formSort.findViewById(R.id.btnDeclineSort);
        dialogBuilder.setView(formSort);
        dialog = dialogBuilder.create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        dialog.show();

        btnDeclineSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnPriceHightToLow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                filter(from,to,dateDepart,TypeSort.PRICELH);
                dialog.dismiss();
            }
        });

        btnPriceLowToHight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                filter(from,to,dateDepart,TypeSort.PRICEHL);
                dialog.dismiss();
            }
        });

        btnTimeLowToHight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                filter(from,to,dateDepart,TypeSort.HOURLH);
                dialog.dismiss();
            }
        });

        btnTimeHightToLow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                filter(from,to,dateDepart,TypeSort.HOURHL);
                dialog.dismiss();
            }
        });

        btnDeclineSort.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}