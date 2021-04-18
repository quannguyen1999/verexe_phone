package com.example.verexe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class Welcome extends AppCompatActivity {
    private static final long SPLASH_SCREEN = 2000;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        imageView = findViewById(R.id.inside_imageview);
        new Handler().postDelayed(()->{
            Intent intent = new Intent(Welcome.this,MainActivity.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View,String>(imageView,"imageVBA");
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Welcome.this,pairs);
                startActivity(intent,options.toBundle());
            }
        },SPLASH_SCREEN);
    }
}