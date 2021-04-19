package com.example.verexe.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verexe.MainActivity;
import com.example.verexe.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterListTrip  extends RecyclerView.Adapter<AdapterListTrip.MyViewHodler>{
    List<String> stringList;
    Context context;
    private Activity activity;

    public AdapterListTrip(Context ct,List<String> stringList,Activity activity){
        context = ct;
        this.stringList = stringList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_trip_row,parent,false);
        return new AdapterListTrip.MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListTrip.MyViewHodler holder, int position) {
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        public MyViewHodler(@NonNull View itemView){
            super(itemView);
//            nameProvince = itemView.findViewById(R.id.nameProvince);
//            linearLayout = itemView.findViewById(R.id.lnItem);
        }
    }

//    public void filterList(ArrayList<String> filteredList) {
//        stringList = filteredList;
//        notifyDataSetChanged();
//    }
}