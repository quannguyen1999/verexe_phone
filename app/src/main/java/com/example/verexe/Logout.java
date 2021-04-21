package com.example.verexe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Logout extends AppCompatActivity {
    private Button btnOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        getSupportActionBar().hide();
        btnOK = findViewById(R.id.btnLogout);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Logout.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}