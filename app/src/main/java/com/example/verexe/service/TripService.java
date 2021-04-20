package com.example.verexe.service;

import android.os.Handler;

import com.example.verexe.HttpRequestCommon;
import com.example.verexe.model.Trip;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class TripService {
    public static List<Trip> searchTrip(String from, String to, LocalDate localDate){
        List<Trip> listArray = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        String url = HttpRequestCommon.url_trip_search;
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("fromLocation", "Hồ Chí Minh");
            postdata.put("toLocation", "Đà Nẵng");
            postdata.put("dateDepart", "2021-04-19");
        } catch(JSONException e){
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
        Request request = new Request.Builder()
                .url(HttpRequestCommon.url_trip_search)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        try {
            Response myResponse = client.newCall(request).execute();
            System.out.println(myResponse.isSuccessful());
            try {
                JSONObject json= new JSONObject(String.valueOf(myResponse));  //your response
                JSONObject objectTripResponse = json.getJSONObject("data");
                JSONArray result = objectTripResponse.getJSONArray("tripResponseList");
                for(int i=0;i<result.length();i++){
                    JSONObject jsonObject = result.getJSONObject(i);
                    Gson gson = new Gson();
//                            YourPojo emp = gson.fromJson(object, YourPojo.class);
                    Trip trip = gson.fromJson(String.valueOf(jsonObject),Trip.class);

                    listArray.add(trip);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}