package com.example.verexe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.verexe.adapter.AdapterListTrip;
import com.example.verexe.adapter.MyAdapter;
import com.example.verexe.service.ProvinceService;

import java.util.Arrays;

public class ListTrip extends AppCompatActivity {
    ImageButton imgBackXP;
    AdapterListTrip adapterListTrip;
    RecyclerView rclView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);
        getSupportActionBar().hide();
        rclView = findViewById(R.id.rclViewListTrip);
        imgBackXP = findViewById(R.id.imgBackXP);

        adapterListTrip = new AdapterListTrip(this, Arrays.asList("a","b","c","D"),ListTrip.this);
        rclView.setAdapter(adapterListTrip);
        rclView.setLayoutManager(new LinearLayoutManager(this));

        imgBackXP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}