package com.example.verexe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.verexe.adapter.AdapterListTrip;
import com.example.verexe.adapter.MyAdapter;
import com.example.verexe.daos.DBManager;
import com.example.verexe.model.Trip;
import com.example.verexe.service.TripService;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    MaterialDatePicker materialDatePicker;
    private static final int FROM = 111;
    private static final int TO = 222;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button btnTimVexe;
    Button btnLogout;
    EditText from;
    EditText to;
    Button dateformat;
    int year;
    int month;
    int day;

    private TextInputLayout textInputLayout;

    private Handler mHandler;

    private DBManager dbManager;

    Menu menu;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbManager = new DBManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        metaData();
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        mHandler = new Handler(Looper.getMainLooper());
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);

        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        handleEvent();

        fakeData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void fakeData() {
        from.setText("Hồ Chí Minh");
        to.setText("Đà Nẵng");
        dateformat.setText(LocalDate.now().toString());
    }

    private void handleEvent() {
        btnTimVexe.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (from.getText().toString().isEmpty()) {
                    showDialog("Vui lòng chọn điếm đón");
                    return;
                }
                if (to.getText().toString().isEmpty()) {
                    showDialog("Vui lòng chọn điếm về");
                    return;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
                LocalDate lcx = LocalDate.now();
                if (dateformat.getText().toString().isEmpty()) {
                    showDialog("Bạn chưa chọn ngày");
                    return;
                }
                try {
                    lcx = LocalDate.parse(dateformat.getText().toString(), formatter);
                } catch (Exception e) {

                }

                if (lcx.isBefore(LocalDate.now())) {
                    showDialog("Ngày không hợp lệ");
                    return;
                }
                try {
                    filterTrip(from.getText().toString(), to.getText().toString(), lcx);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, startingPlace.class);
                startActivityForResult(intent, FROM);
                return;
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, startingPlace.class);
                startActivityForResult(intent, TO);
                return;
            }
        });
        final long today = MaterialDatePicker.todayInUtcMilliseconds();
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a Date");
        builder.setSelection(today);
        materialDatePicker = builder.build();
        dateformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                dateformat.setText(materialDatePicker.getHeaderText());
            }
        });


    }

    private void showDialog(String error) {
        Button btnBack;
        TextView txtError;
        Dialog dialog = new Dialog(MainActivity.this);
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

    private void metaData() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        textInputLayout = findViewById(R.id.datecome);
        btnTimVexe = findViewById(R.id.btnTimVexe);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        dateformat = findViewById(R.id.dateformatID);
        menu = navigationView.getMenu();
        if (dbManager.getAuth() != null) {
            menu.findItem(R.id.nav_login).setVisible(false);
        } else {
            menu.findItem(R.id.nav_logout).setVisible(false);
            menu.findItem(R.id.nav_info).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_login:
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                dbManager.deleteAll();
                Intent intentX = new Intent(MainActivity.this, Logout.class);
                startActivity(intentX);
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FROM) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra("add");
                from.setText(bundle.getString("value"));
            }
        } else if (requestCode == TO) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getBundleExtra("add");
                to.setText(bundle.getString("value"));
            }
        }
    }

    List<Trip> listArray;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Trip> filterTrip(String from, String to, LocalDate dateDepart) throws IOException {
        listArray = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        String url = HttpRequestCommon.url_trip_search;
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("fromLocation", from);
            postdata.put("toLocation", to);
            postdata.put("dateDepart", dateDepart.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
        Request request = new Request.Builder()
                .url(HttpRequestCommon.url_trip_search)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Response myResponse = client.newCall(request).execute();
        try {
            JSONObject json = new JSONObject(myResponse.body().string());  //your response
            JSONObject objectTripResponse = json.getJSONObject("data");
            JSONArray result = objectTripResponse.getJSONArray("tripResponseList");
            for (int i = 0; i < result.length(); i++) {
                JSONObject jsonObject = result.getJSONObject(i);
                Gson gson = new Gson();
                Trip trip = gson.fromJson(String.valueOf(jsonObject), Trip.class);
                listArray.add(trip);
            }
            if (listArray.size() <= 0) {
                showDialog("Không có chuyến nào");
            } else {
                Intent intent = new Intent(MainActivity.this, ListTrip.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("from", from);
                bundle.putSerializable("to", to);
                bundle.putSerializable("date", dateDepart);
                intent.putExtra("search", bundle);
                startActivityForResult(intent, 555);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            showDialog("Không có chuyến nào");
        }
        return listArray;
    }
}