package com.example.verexe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.verexe.adapter.MyAdapter;
import com.example.verexe.service.ProvinceService;

import java.lang.reflect.Array;
import java.util.Arrays;

public class startingPlace extends AppCompatActivity {
    String s1[] = {"4","5","8","6"}, s2[] = {"78"};

    RecyclerView rclView;
    ImageButton imgBackXP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_place);
        rclView = findViewById(R.id.rclView);
        imgBackXP = findViewById(R.id.imgBackXP);
        getSupportActionBar().hide();
        System.out.println(Arrays.asList("ok","ok").size());
        for (String s : ProvinceService.getProvince()) {
            System.out.println(s);
        }
        MyAdapter myAdapter = new MyAdapter(this,ProvinceService.getProvince());
        rclView.setAdapter(myAdapter);
        rclView.setLayoutManager(new LinearLayoutManager(this));

        imgBackXP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}