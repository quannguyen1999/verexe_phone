package com.example.verexe;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.verexe.adapter.AdapterListTrip;
import com.example.verexe.adapter.MyAdapter;
import com.example.verexe.model.Coach;
import com.example.verexe.model.Fare;
import com.example.verexe.model.Seat;
import com.example.verexe.model.SeatType;
import com.example.verexe.model.Trip;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListSeat extends AppCompatActivity implements View.OnClickListener {

    public static Float price1 = 200000.f;
    public static Float price2 = 200000.f;
    public static Float price3 = 200000.f;
    public static Float price4 = 200000.f;
    public static Float price5 = 200000.f;
    public static Float price6 = 200000.f;
    public static Float price7 = 200000.f;
    public static Float price8 = 200000.f;
    public static Float price9 = 200000.f;
    public static Float price10 = 200000.f + 200000f;
    public static Float price11 = 200000.f + 100000f;
    public static Float price12 = 200000.f;
    public static Float price13 = 200000.f + 20000f;
    public static Float price14 = 200000.f + 80000f;
    public static Float price15 = 200000.f;
    public static Float price16 = 200000.f + 1000000f;
    public static Float price17 = 200000.f + 10000f;
    public static Float price18 = 200000.f + 5000f;
    public static Float price19 = 200000.f + 40000f;
    public static Float price20 = 200000.f + 6000f;

    public static Fare fare1 = new Fare(price1, 0, 0);
    public static Fare fare2 = new Fare(price2, 0, 0);
    public static Fare fare3 = new Fare(price3, 0, 0);
    public static Fare fare4 = new Fare(price4, 0, 0);
    public static Fare fare5 = new Fare(price5, 0, 0);
    public static Fare fare6 = new Fare(price6, 0, 0);
    public static Fare fare7 = new Fare(price7, 0, 0);
    public static Fare fare8 = new Fare(price8, 0, 0);
    public static Fare fare9 = new Fare(price9, 0, 0);
    public static Fare fare10 = new Fare(price10, 0, 0);
    public static Fare fare11 = new Fare(price11, 0, 0);
    public static Fare fare12 = new Fare(price12, 0, 0);
    public static Fare fare13 = new Fare(price13, 0, 0);
    public static Fare fare14 = new Fare(price14, 0, 0);
    public static Fare fare15 = new Fare(price15, 0, 0);
    public static Fare fare16 = new Fare(price16, 0, 0);
    public static Fare fare17 = new Fare(price17, 0, 0);
    public static Fare fare18 = new Fare(price18, 0, 0);
    public static Fare fare19 = new Fare(price19, 0, 0);
    public static Fare fare20 = new Fare(price20, 0, 0);

    public static Seat seat1 = new Seat(2, 0, "A1", 1, 1, 1, 1, SeatType.CHUADAT, fare1.getOriginal(), fare1);
    public static Seat seat2 = new Seat(2, 0, "A2", 1, 3, 1, 1, SeatType.DADAT, fare2.getOriginal(), fare2);
    public static Seat seat3 = new Seat(2, 0, "A3", 1, 5, 1, 1, SeatType.DADAT, fare3.getOriginal(), fare3);
    public static Seat seat4 = new Seat(2, 0, "A4", 2, 1, 1, 1, SeatType.CHUADAT, fare4.getOriginal(), fare4);
    public static Seat seat5 = new Seat(2, 0, "A5", 2, 3, 1, 1, SeatType.DADAT, fare5.getOriginal(), fare5);
    public static Seat seat6 = new Seat(2, 0, "A6", 2, 5, 1, 1, SeatType.DADAT, fare6.getOriginal(), fare6);
    public static Seat seat7 = new Seat(2, 0, "A7", 3, 1, 1, 1, SeatType.DADAT, fare7.getOriginal(), fare7);
    public static Seat seat8 = new Seat(2, 0, "A8", 3, 3, 1, 1, SeatType.CHUADAT, fare8.getOriginal(), fare8);
    public static Seat seat9 = new Seat(2, 0, "A9", 3, 5, 1, 1, SeatType.CHUADAT, fare9.getOriginal(), fare9);
    public static Seat seat10 = new Seat(2, 0, "A10", 4, 1, 1, 1, SeatType.DADAT, fare10.getOriginal(), fare10);
    public static Seat seat11 = new Seat(2, 0, "A11", 4, 3, 1, 1, SeatType.DADAT, fare11.getOriginal(), fare11);
    public static Seat seat12 = new Seat(2, 0, "A12", 4, 5, 1, 1, SeatType.CHUADAT, fare12.getOriginal(), fare12);
    public static Seat seat13 = new Seat(2, 0, "A13", 5, 1, 1, 1, SeatType.CHUADAT, fare13.getOriginal(), fare13);
    public static Seat seat14 = new Seat(2, 0, "A14", 5, 3, 1, 1, SeatType.CHUADAT, fare14.getOriginal(), fare14);
    public static Seat seat15 = new Seat(2, 0, "A15", 5, 5, 1, 1, SeatType.CHUADAT, fare15.getOriginal(), fare15);
    public static Seat seat16 = new Seat(2, 0, "C1", 6, 1, 1, 1, SeatType.CHUADAT, fare16.getOriginal(), fare16);
    public static Seat seat17 = new Seat(2, 0, "C2", 6, 2, 1, 1, SeatType.CHUADAT, fare17.getOriginal(), fare17);
    public static Seat seat17_0 = new Seat(2, 0, "C3", 6, 3, 1, 1, SeatType.CHUADAT, fare18.getOriginal(), fare18);
    public static Seat seat18 = new Seat(2, 0, "C4", 6, 4, 1, 1, SeatType.DADAT, fare19.getOriginal(), fare19);
    public static Seat seat19 = new Seat(2, 0, "C5", 6, 5, 1, 1, SeatType.CHUADAT, fare20.getOriginal(), fare20);

    public static Seat seat20 = new Seat(2, 0, "B1", 1, 1, 1, 1, SeatType.CHUADAT, fare1.getOriginal(), fare1);
    public static Seat seat21 = new Seat(2, 0, "B2", 1, 3, 1, 1, SeatType.CHUADAT, fare2.getOriginal(), fare2);
    public static Seat seat22 = new Seat(2, 0, "B3", 1, 5, 1, 1, SeatType.CHUADAT, fare3.getOriginal(), fare3);
    public static Seat seat23 = new Seat(2, 0, "B4", 2, 1, 1, 1, SeatType.DADAT, fare4.getOriginal(), fare4);
    public static Seat seat24 = new Seat(2, 0, "B5", 2, 3, 1, 1, SeatType.CHUADAT, fare5.getOriginal(), fare5);
    public static Seat seat25 = new Seat(2, 0, "B6", 2, 5, 1, 1, SeatType.CHUADAT, fare6.getOriginal(), fare6);
    public static Seat seat26 = new Seat(2, 0, "B7", 3, 1, 1, 1, SeatType.CHUADAT, fare7.getOriginal(), fare7);
    public static Seat seat27 = new Seat(2, 0, "B8", 3, 3, 1, 1, SeatType.CHUADAT, fare8.getOriginal(), fare8);
    public static Seat seat28 = new Seat(2, 0, "B9", 3, 5, 1, 1, SeatType.CHUADAT, fare9.getOriginal(), fare9);
    public static Seat seat29 = new Seat(2, 0, "B10", 4, 1, 1, 1, SeatType.CHUADAT, fare10.getOriginal(), fare10);
    public static Seat seat30 = new Seat(2, 0, "B11", 4, 3, 1, 1, SeatType.CHUADAT, fare11.getOriginal(), fare11);
    public static Seat seat31 = new Seat(2, 0, "B12", 4, 5, 1, 1, SeatType.CHUADAT, fare12.getOriginal(), fare12);
    public static Seat seat32 = new Seat(2, 0, "B13", 5, 1, 1, 1, SeatType.CHUADAT, fare13.getOriginal(), fare13);
    public static Seat seat33 = new Seat(2, 0, "B14", 5, 3, 1, 1, SeatType.CHUADAT, fare14.getOriginal(), fare14);
    public static Seat seat34 = new Seat(2, 0, "B15", 5, 5, 1, 1, SeatType.CHUADAT, fare15.getOriginal(), fare15);

    public static Seat seat35 = new Seat(2, 0, "C6", 6, 1, 1, 1, SeatType.CHUADAT, fare16.getOriginal(), fare16);
    public static Seat seat36 = new Seat(2, 0, "C7", 6, 2, 1, 1, SeatType.CHUADAT, fare17.getOriginal(), fare17);
    public static Seat seat37 = new Seat(2, 0, "C8", 6, 3, 1, 1, SeatType.CHUADAT, fare18.getOriginal(), fare18);
    public static Seat seat38 = new Seat(2, 0, "C9", 6, 4, 1, 1, SeatType.CHUADAT, fare19.getOriginal(), fare19);
    public static Seat seat39 = new Seat(2, 0, "C10", 6, 5, 1, 1, SeatType.CHUADAT, fare20.getOriginal(), fare20);


    public static ArrayList<Seat> seatTop = new ArrayList<>();

    {
        seatTop.add(seat1);
        seatTop.add(seat2);
        seatTop.add(seat3);
        seatTop.add(seat4);
        seatTop.add(seat5);
        seatTop.add(seat6);
        seatTop.add(seat7);
        seatTop.add(seat8);
        seatTop.add(seat9);
        seatTop.add(seat10);
        seatTop.add(seat11);
        seatTop.add(seat12);
        seatTop.add(seat13);
        seatTop.add(seat14);
        seatTop.add(seat15);
        seatTop.add(seat16);
        seatTop.add(seat17);
        seatTop.add(seat17_0);
        seatTop.add(seat18);
        seatTop.add(seat19);
    }

    public static ArrayList<Seat> seatBottom = new ArrayList<>();
    {
        seatBottom.add(seat20);
        seatBottom.add(seat21);
        seatBottom.add(seat22);
        seatBottom.add(seat23);
        seatBottom.add(seat24);
        seatBottom.add(seat25);
        seatBottom.add(seat26);
        seatBottom.add(seat27);
        seatBottom.add(seat28);
        seatBottom.add(seat29);
        seatBottom.add(seat30);
        seatBottom.add(seat31);
        seatBottom.add(seat32);
        seatBottom.add(seat33);
        seatBottom.add(seat34);
        seatBottom.add(seat35);
        seatBottom.add(seat36);
        seatBottom.add(seat37);
        seatBottom.add(seat38);
        seatBottom.add(seat39);
    }

    Button btnHuy;

    ImageButton imgBack;

    ViewGroup layout;
//    U chuadat
//    R dat
//    A dang dat
    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;

    int FLOOR_1 = 5;
    int FLOOR_2 = 6;
    String selectedIds = "";

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_seat);
        getSupportActionBar().hide();
        layout = findViewById(R.id.layoutSeat);
        mHandler = new Handler(Looper.getMainLooper());
//        myAdapter = new MyAdapter(this,new ArrayList<>(),startingPlace.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("add");
        String valueIdTrip = bundle.getString("id");
        filter(valueIdTrip);
        btnHuy = findViewById(R.id.btnHuy);
        imgBack = findViewById(R.id.imgBack);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        String valueSeat = "";
//        handleFloor(layout, seatTop, seatBottom);
    }

    List<String> listArray;
    private void filter(String text) {
        listArray = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        String url = HttpRequestCommon.url_coach_by_id+text;
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
                        JSONObject json= new JSONObject(myResponse);  //your response
                        JSONObject objectTripResponse = json.getJSONObject("data");

                            JSONObject jsonObject = objectTripResponse;
                            Gson gson = new Gson();
                            Coach coach = gson.fromJson(String.valueOf(jsonObject),Coach.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println(coach);
                                    handleFloor(layout,coach.getFloor().get(0).getSeats(),coach.getFloor().size() >=2 ? coach.getFloor().get(1).getSeats() : null);
                                }
                            });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void handleFloor(ViewGroup viewGroup, List<Seat> tripList, List<Seat> seatFloorTwo) {
        int number = tripList.get(tripList.size() - 1).getRow_num();
        int colNumCurrent = tripList.get(tripList.size() - 1).getRow_num();
        String valueSeat = "";
//        for (int i = 0; i < seatTop.size(); i++) {
//            if (number == seatTop.get(i).getRow_num()) {
//                if (colNumCurrent != seatTop.get(i).getCol_num()) {
//                    do {
//                        valueSeat = valueSeat + "_";
//                        colNumCurrent++;
//                    } while (colNumCurrent != seatTop.get(i).getCol_num());
//                }
//                valueSeat = valueSeat + "U";
//                colNumCurrent++;
//            } else {
//                colNumCurrent = 1;
//                valueSeat = valueSeat + "/";
//                number = seatTop.get(i).getRow_num();
//                i--;
//            }
//        }
//        seats = valueSeat;
        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setGravity(Gravity.CENTER);
        viewGroup.addView(layoutSeat);
        LinearLayout layout = null;

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layoutSeat.addView(layout);
        TextView viewx1 = new TextView(this);
        LinearLayout.LayoutParams layoutParamsx1 = new LinearLayout.LayoutParams(seatSize, seatSize);
        layoutParamsx1.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
        viewx1.setLayoutParams(layoutParamsx1);
        viewx1.setPadding(0, 0, 0, 2 * seatGaping);
        viewx1.setGravity(Gravity.CENTER);
        viewx1.setText("Tầng 1");
        viewx1.setTextSize(15);
        viewx1.setTypeface(null, Typeface.BOLD);
        viewx1.setTextColor(Color.BLACK);
        viewx1.setTag(STATUS_BOOKED);
        viewx1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
        layout.addView(viewx1);
        seatViewList.add(viewx1);

        for (int i = 0; i < tripList.size(); i++) {
            if (number == tripList.get(i).getRow_num()) {
                if (colNumCurrent != tripList.get(i).getCol_num()) {
                    do {
                        valueSeat = valueSeat + "_";
                        colNumCurrent++;
                        TextView view = new TextView(this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                        layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                        view.setLayoutParams(layoutParams);
                        view.setBackgroundColor(Color.TRANSPARENT);
                        view.setText("");
                        layout.addView(view);
                    } while (colNumCurrent != tripList.get(i).getCol_num());
                }
                valueSeat = valueSeat + "U";
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 2 * seatGaping);
                view.setId(i);
                view.setGravity(Gravity.CENTER);
                if (tripList.get(i).getIs_available() == SeatType.DADAT) {
                    view.setBackgroundResource(R.drawable.seat_order);
                } else {
                    view.setBackgroundResource(R.drawable.seat_not_order);
                }
                view.setTextColor(Color.BLACK);
                view.setTag(FLOOR_1);
                view.setText(tripList.get(i).getSeat_code());
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
                colNumCurrent++;
            } else {
                colNumCurrent = 1;
                valueSeat = valueSeat + "/";
                number = tripList.get(i).getRow_num();
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
                i--;
            }
        }


        if (seatFloorTwo.size() >= 1) {
            int space = 0;
            layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layoutSeat.addView(layout);
                TextView viewx = new TextView(this);
                LinearLayout.LayoutParams layoutParamsx = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParamsx.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                viewx.setLayoutParams(layoutParamsx);
                viewx.setPadding(0, 0, 0, 2 * seatGaping);
                viewx.setGravity(Gravity.CENTER);
                viewx.setText("Tầng 2");
                viewx.setTextSize(15);
                viewx.setTypeface(null, Typeface.BOLD);
                viewx.setTextColor(Color.BLACK);
                viewx.setTag(STATUS_BOOKED);
                viewx.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                layout.addView(viewx);
                seatViewList.add(viewx);
            number = seatFloorTwo.get(seatFloorTwo.size() - 1).getRow_num();
            colNumCurrent = seatFloorTwo.get(seatFloorTwo.size() - 1).getRow_num();
            for (int i = 0; i < seatFloorTwo.size(); i++) {
                if (number == seatFloorTwo.get(i).getRow_num()) {
                    if (colNumCurrent != seatFloorTwo.get(i).getCol_num()) {
                        do {
                            valueSeat = valueSeat + "_";
                            colNumCurrent++;
                            TextView view = new TextView(this);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                            layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                            view.setLayoutParams(layoutParams);
                            view.setBackgroundColor(Color.TRANSPARENT);
                            view.setText("");
                            layout.addView(view);
                        } while (colNumCurrent != seatFloorTwo.get(i).getCol_num());
                    }
                    valueSeat = valueSeat + "U";
                    TextView view = new TextView(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                    view.setLayoutParams(layoutParams);
                    view.setPadding(0, 0, 0, 2 * seatGaping);
                    view.setId(i);
                    view.setGravity(Gravity.CENTER);
                    if (seatFloorTwo.get(i).getIs_available() == SeatType.DADAT) {
                        view.setBackgroundResource(R.drawable.seat_order);
                    } else {
                        view.setBackgroundResource(R.drawable.seat_not_order);
                    }
                    view.setTextColor(Color.BLACK);
                    view.setTag(FLOOR_2);
                    view.setText(seatFloorTwo.get(i).getSeat_code());
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                    layout.addView(view);
                    seatViewList.add(view);
                    view.setOnClickListener(this);
                    colNumCurrent++;
                } else {
                    colNumCurrent = 1;
                    valueSeat = valueSeat + "/";
                    number = seatFloorTwo.get(i).getRow_num();
                    layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layoutSeat.addView(layout);
                    i--;
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.schoolbus);
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.schoolbus);
            }
        } else if ((int) view.getTag() == FLOOR_1) {
            if (seatTop.get(view.getId()).getIs_available() == SeatType.CHUADAT) {
                if (view.getBackground().getConstantState() == getResources().getDrawable(R.drawable.seat_is_irder).getConstantState()) {
                    view.setBackgroundResource(R.drawable.seat_not_order);
                } else {
                    view.setBackgroundResource(R.drawable.seat_is_irder);
                }
            }
        } else if ((int) view.getTag() == FLOOR_2) {
            if (seatBottom.get(view.getId()).getIs_available() == SeatType.CHUADAT) {
                if (view.getBackground().getConstantState() == getResources().getDrawable(R.drawable.seat_is_irder).getConstantState()) {
                    view.setBackgroundResource(R.drawable.seat_not_order);
                } else {
                    view.setBackgroundResource(R.drawable.seat_is_irder);
                }
            }
        }
    }
}