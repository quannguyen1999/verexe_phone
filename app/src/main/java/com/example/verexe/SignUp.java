package com.example.verexe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private Button btnDangKyDK;
    private ImageButton imgBackDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        metaData();
        handleEvent();
    }

    private void metaData(){
//        btnDangNhap = findViewById(R.id.btnDangNhap);
//        txtPhone = findViewById(R.id.phone);
        btnDangKyDK = findViewById(R.id.btnDangKyDK);
        imgBackDK = findViewById(R.id.imgBackDK);
    }

    private void handleEvent(){
        btnDangKyDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,login.class);
                finish();
                startActivity(intent);
                return;
            }
        });
        imgBackDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });
    }

}