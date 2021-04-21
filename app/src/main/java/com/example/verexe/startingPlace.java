package com.example.verexe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.verexe.adapter.MyAdapter;
import com.example.verexe.service.ProvinceService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class startingPlace extends AppCompatActivity {
    private ArrayList<String> listProvince;

    RecyclerView rclView;
    ImageButton imgBackXP;
    EditText txtFind;

    MyAdapter myAdapter;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_place);
        rclView = findViewById(R.id.rclView);
        imgBackXP = findViewById(R.id.imgBackXP);
        txtFind = findViewById(R.id.txtFind);
        getSupportActionBar().hide();
        listProvince = new ArrayList<>();
        mHandler = new Handler(Looper.getMainLooper());
        myAdapter = new MyAdapter(this,new ArrayList<>(),startingPlace.this);
        filter("");
        rclView.setAdapter(myAdapter);
        rclView.setLayoutManager(new LinearLayoutManager(this));
        imgBackXP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    List<String> listArray;
    private void filter(String text) {
        listArray = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        String url = HttpRequestCommon.url_province;
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
                        JSONObject json = new JSONObject(myResponse);  //your response
                        JSONArray result = json.getJSONArray("data");
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject jsonObject = result.getJSONObject(i);
                            if (jsonObject.getString("name").toLowerCase().contains(text.toLowerCase())) {
                                listArray.add(jsonObject.getString("name"));
                            }
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                myAdapter.filterList((ArrayList<String>) listArray);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static final int FROM = 111;
    private static final int TO = 222;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        System.out.println("ok");
//        if(requestCode == FROM){
//            if(resultCode == RESULT_OK){
//              filter("");
//            }
//        }else if(requestCode == TO){
//            if(resultCode == RESULT_OK){
//                filter("");
//            }
//        }
//    }
}