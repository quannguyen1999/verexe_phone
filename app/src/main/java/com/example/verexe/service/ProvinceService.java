package com.example.verexe.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProvinceService {
    public static List<String> getProvince(){
        List<String> listArray = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://xedike.ddnsking.com/api/v1/provinces";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String myResponse = response.body().string();
                    try {
                        JSONObject json= new JSONObject(myResponse);  //your response
                        JSONArray result = json.getJSONArray("data");
                        for(int i=0;i<result.length();i++){
                            JSONObject jsonObject = result.getJSONObject(i);
                            listArray.add(jsonObject.getString("name"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    System.out.println(myResponse);
                }
            }
        });
        return listArray;
    }
}
