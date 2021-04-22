package com.example.verexe;


import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListSeat extends AppCompatActivity implements View.OnClickListener {
    static Set<Seat> seatListOrder = new HashSet<>();

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

    private LinearLayout lnItemSeat;
    private LinearLayout lnItemNotSeat;
    private Button btnComfirmOrder;
    private TextView txtListSeat;
    private TextView txtTotalPrice;

    private List<Seat> seatTop = new ArrayList<>();
    private List<Seat> seatBottom = new ArrayList<>();

    private String valueIdTrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_seat);
        getSupportActionBar().hide();
        layout = findViewById(R.id.layoutSeat);
        mHandler = new Handler(Looper.getMainLooper());

        metaData();
        handleEvent();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("add");
        valueIdTrip = bundle.getString("id");
        seatListOrder.clear();
        filter(valueIdTrip);
//        if(seatListOrder.size() >=1 ){
//            lnItemSeat.setVisibility(View.VISIBLE);
//            btnComfirmOrder.setVisibility(View.VISIBLE);
//            lnItemNotSeat.setVisibility(View.GONE);
//        }
    }

    private void handleEvent() {
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

        btnComfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSeat.this,ListFromLocationMain.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", valueIdTrip);
                List<Seat> seatListOrderArray = new ArrayList<>();
                for (Seat seat : seatListOrder) {
                    seatListOrderArray.add(seat);
                }
                bundle.putParcelableArrayList("seat_order", (ArrayList<? extends Parcelable>) seatListOrderArray);
                intent.putExtra("add", bundle);
                finish();
                startActivity(intent);
            }
        });
    }

    private void metaData() {
        btnHuy = findViewById(R.id.btnHuy);
        imgBack = findViewById(R.id.imgBack);
        lnItemSeat = findViewById(R.id.lnItemSeat);
        lnItemNotSeat = findViewById(R.id.lnItemNotSeat);
        btnComfirmOrder = findViewById(R.id.btnComfirmOrder);
        txtListSeat = findViewById(R.id.txtListSeat);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
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
                                    seatTop.addAll(coach.getFloor().get(0).getSeats());
                                    if(coach.getFloor().size() > 1){
                                        seatBottom.addAll(coach.getFloor().get(1).getSeats());
                                    }
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
                    seatListOrder.remove(seatTop.get(view.getId()));
                    view.setBackgroundResource(R.drawable.seat_not_order);
                } else {
                    if(seatListOrder.size() > 4 ){
                        showDialog("Chỉ được đặt tối đa 5 ghế");
                        return;
                    }
                    seatListOrder.add(seatTop.get(view.getId()));
                    view.setBackgroundResource(R.drawable.seat_is_irder);
                }
            }
        } else if ((int) view.getTag() == FLOOR_2) {
            if (seatBottom.get(view.getId()).getIs_available() == SeatType.CHUADAT) {
                if (view.getBackground().getConstantState() == getResources().getDrawable(R.drawable.seat_is_irder).getConstantState()) {
                    seatListOrder.remove(seatBottom.get(view.getId()));
                    view.setBackgroundResource(R.drawable.seat_not_order);
                } else {
                    if(seatListOrder.size() > 4 ){
                        showDialog("Chỉ được đặt tối đa 5 ghế");
                        return;
                    }
                    seatListOrder.add(seatBottom.get(view.getId()));
                    view.setBackgroundResource(R.drawable.seat_is_irder);
                }
            }
        }
        String listSeat = "";
        int totalPrice = 0;
        int count = 0;
        txtListSeat.setText("");
        txtTotalPrice.setText("");
        if(seatListOrder.size() >= 1){
            lnItemSeat.setVisibility(View.VISIBLE);
            btnComfirmOrder.setVisibility(View.VISIBLE);
            lnItemNotSeat.setVisibility(View.GONE);
            for (Seat seat : seatListOrder) {
                if(count >= 1){
                    listSeat = listSeat+","+seat.getSeat_code();
                }else{
                    listSeat = seat.getSeat_code();
                    count++;
                }
                totalPrice+=seat.getFares().getOriginal();
            }
            txtListSeat.setText(listSeat);
            DecimalFormat df = new DecimalFormat("#,###,###");
//            holder.txtPrice.setText(df.format(stringList.get(position).getPrice()));
            txtTotalPrice.setText(String.valueOf(df.format(totalPrice))+" đ");
        }else{
            lnItemSeat.setVisibility(View.GONE);
            btnComfirmOrder.setVisibility(View.GONE);
            lnItemNotSeat.setVisibility(View.VISIBLE);
        }
    }
    private void showDialog(String error) {
        Button btnBack;
        TextView txtError;
        Dialog dialog = new Dialog(ListSeat.this);
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