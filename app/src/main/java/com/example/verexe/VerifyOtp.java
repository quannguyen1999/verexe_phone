package com.example.verexe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.chaos.view.PinView;

public class VerifyOtp extends AppCompatActivity {
    private ImageButton imgBack;
    String verificationCodeBySystem;
    private Button btnVerify;
    private PinView pinview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().hide();
        metaData();
        handleEvent();
    }

    private void metaData(){
        imgBack = findViewById(R.id.imgBack);
        btnVerify = findViewById(R.id.btnVerify);
        pinview = findViewById(R.id.pinview);
    }

    private void handleEvent(){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinview.getInputType();
                System.out.println(pinview.getText().toString());
            }
        });
    }
}