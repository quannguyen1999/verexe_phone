package com.example.verexe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.verexe.adapter.AdapterListTrip;
import com.example.verexe.daos.DBManager;
import com.example.verexe.model.AuthResponse;
import com.example.verexe.model.CustomError;
import com.example.verexe.model.Trip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class login extends AppCompatActivity {
    static String account_required_password = "this account is required password";
    String verificationCodeBySystem;
    private static final int LOGIN = 999;
    private static final int SIGNUP = 888;
    private Button btnDangNhap;
    private Button btnDangKy;

    private Button btnSignUpLG;
    private Button btnLoginLG;

    private ImageButton imgBack;
    private TextInputEditText txtPhone;
    private TextInputLayout txtilPassword;
    private TextView txtNotHasAccount;
    private TextView txtHasAccount;
    private TextView titleLG;
    private TextView titleDK;


    private Handler mHandler;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbManager = new DBManager(this);
        dbManager.deleteAll();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mHandler = new Handler(Looper.getMainLooper());
        metaData();
        txtilPassword.setVisibility(View.GONE);
        handleEvent();
    }

    private void metaData() {
        txtilPassword = findViewById(R.id.txtilPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtPhone = findViewById(R.id.phone);
        btnSignUpLG = findViewById(R.id.btnSignUpLG);
        imgBack = findViewById(R.id.imgBack);

        btnDangKy = findViewById(R.id.btnDangKy);
        btnLoginLG = findViewById(R.id.btnLoginLG);

          txtNotHasAccount = findViewById(R.id.txtNotHasAccount);
          txtHasAccount = findViewById(R.id.txtHasAccount);

          titleLG = findViewById(R.id.titleLG);
          titleDK = findViewById(R.id.titleDK);
    }

    private void handleEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();
                String url = HttpRequestCommon.url_user_signin;
                MediaType MEDIA_TYPE = MediaType.parse("application/json");
                JSONObject postdata = new JSONObject();
                try {
                    postdata.put("phone", txtPhone.getText().toString());
                    if (txtilPassword.getEditText().getText().toString().isEmpty() == false) {
                        postdata.put("password", txtilPassword.getEditText().getText().toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
                Request request = new Request.Builder()
                        .url(HttpRequestCommon.url_user_signin)
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
                        String myResponse = response.body().string();
                        if (response.code() == 400) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    JSONObject json = null;  //your response
                                    try {
                                        json = new JSONObject(myResponse);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    JSONArray result = null;
                                    try {
                                        result = json.getJSONArray("error");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Gson gson = new Gson();
                                    for (int i = 0; i < result.length(); i++) {
                                        JSONObject jsonObject = null;
                                        try {
                                            jsonObject = result.getJSONObject(i);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        CustomError error = gson.fromJson(String.valueOf(jsonObject), CustomError.class);
                                        if (error.getMessage().equalsIgnoreCase(account_required_password)) {
                                            txtilPassword.setVisibility(View.VISIBLE);
                                            showDialog("Please enter password");
                                            txtilPassword.requestFocus();
                                            return;
                                        }
                                        showDialog(error.getMessage());
                                        return;
                                    }
                                    ;
                                }
                            });
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject json = new JSONObject(myResponse);  //your response
                                        JSONObject getJsonData = json.getJSONObject("data");
                                        Gson gson = new Gson();
                                        AuthResponse authResponse = gson.fromJson(String.valueOf(getJsonData), AuthResponse.class);
                                        dbManager.addAuth(authResponse);
                                        Intent intent = new Intent(login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();
                String url = HttpRequestCommon.url_user_signin;
                MediaType MEDIA_TYPE = MediaType.parse("application/json");
                JSONObject postdata = new JSONObject();
                try {
                    postdata.put("phone", txtPhone.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
                Request request = new Request.Builder()
                        .url(HttpRequestCommon.url_user_signup)
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
                        String myResponse = response.body().string();
                        if (response.code() == 400) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    JSONObject json = null;  //your response
                                    try {
                                        json = new JSONObject(myResponse);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    JSONArray result = null;
                                    try {
                                        result = json.getJSONArray("error");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Gson gson = new Gson();
                                    for (int i = 0; i < result.length(); i++) {
                                        JSONObject jsonObject = null;
                                        try {
                                            jsonObject = result.getJSONObject(i);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        CustomError error = gson.fromJson(String.valueOf(jsonObject), CustomError.class);
//                                        if (error.getMessage().equalsIgnoreCase(account_required_password)) {
//                                            txtilPassword.setVisibility(View.VISIBLE);
//                                            showDialog("Please enter password");
//                                            txtilPassword.requestFocus();
//                                            return;
//                                        }
                                        showDialog(error.getMessage());

                                        return;
                                    };
                                }
                            });
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        JSONObject json = new JSONObject(myResponse);  //your response
                                        JSONObject getJsonData = json.getJSONObject("data");
                                        Gson gson = new Gson();
                                        AuthResponse authResponse = gson.fromJson(String.valueOf(getJsonData), AuthResponse.class);
                                        dbManager.addAuth(authResponse);
                                        Intent intent = new Intent(login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            }
        });
        btnSignUpLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtilPassword.setVisibility(View.GONE);
                btnDangNhap.setVisibility(View.GONE);
                btnSignUpLG.setVisibility(View.GONE);
                btnDangKy.setVisibility(View.VISIBLE);
                btnLoginLG.setVisibility(View.VISIBLE);
                txtHasAccount.setVisibility(View.VISIBLE);
                txtNotHasAccount.setVisibility(View.GONE);
                titleDK.setVisibility(View.VISIBLE);
                titleLG.setVisibility(View.GONE);
                return;
            }
        });

        btnLoginLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtilPassword.setVisibility(View.GONE);
                btnDangNhap.setVisibility(View.VISIBLE);
                btnSignUpLG.setVisibility(View.VISIBLE);
                btnDangKy.setVisibility(View.GONE);
                btnLoginLG.setVisibility(View.GONE);
                txtHasAccount.setVisibility(View.GONE);
                txtNotHasAccount.setVisibility(View.VISIBLE);
                titleDK.setVisibility(View.GONE);
                titleLG.setVisibility(View.VISIBLE);
                return;
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showDialog(String error) {
        Button btnBack;
        TextView txtError;
        Dialog dialog = new Dialog(login.this);
        dialog.setContentView(R.layout.error_layout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btnBack = dialog.findViewById(R.id.btnbackDA);
        txtError = dialog.findViewById(R.id.txtError);
        txtError.setText(error);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}