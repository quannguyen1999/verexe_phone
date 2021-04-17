package com.example.verexe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    String verificationCodeBySystem;
    private static final int LOGIN = 999;
    private Button btnDangNhap;
    private TextInputLayout txtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        metaData();
        handleEvent();
    }

    private void metaData(){
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtPhone = findViewById(R.id.phone);
    }

    private void handleEvent(){
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtPhone.getEditText().getText().toString().isEmpty()) {
                    Toast.makeText(login.this, "please enter phone", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(login.this,VerifyOtp.class);
                startActivityForResult(intent,LOGIN);
                return;
            }
        });
    }
}