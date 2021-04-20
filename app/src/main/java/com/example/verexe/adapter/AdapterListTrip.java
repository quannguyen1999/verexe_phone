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

import com.example.verexe.ListSeat;
import com.example.verexe.MainActivity;
import com.example.verexe.R;
import com.example.verexe.model.Trip;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterListTrip  extends RecyclerView.Adapter<AdapterListTrip.MyViewHodler>{
    List<Trip> stringList;
    Context context;
    private Activity activity;

    public AdapterListTrip(Context ct,List<Trip> stringList,Activity activity){
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
        String fromHour = stringList.get(position).getDeparture().getHour() <= 9 ?
                "0"+stringList.get(position).getDeparture().getHour() :
                String.valueOf(stringList.get(position).getDeparture().getHour());
        String toHour = stringList.get(position).getArrival().getHour() <= 9 ?
                "0"+stringList.get(position).getArrival().getHour() :
                String.valueOf(stringList.get(position).getArrival().getHour());
        String fromMinute = stringList.get(position).getDeparture().getMinute() <= 9 ?
                "0"+stringList.get(position).getDeparture().getMinute() :
                String.valueOf(stringList.get(position).getDeparture().getMinute());
        String toMinute = stringList.get(position).getArrival().getMinute() <= 9 ?
                "0"+stringList.get(position).getArrival().getMinute() :
                String.valueOf(stringList.get(position).getArrival().getMinute());
        holder.txtTimeBegin.setText(fromHour+":"+fromMinute);
        holder.txtTimeFinish.setText(toHour+":"+toMinute);
        holder.txtFromProject.setText(stringList.get(position).getDeparture().getNameProject());
        holder.txtToProject.setText(stringList.get(position).getArrival().getNameProject());
        holder.txtTitle.setText(stringList.get(position).getTitle());
        holder.txtRate.setText(String.valueOf(stringList.get(position).getRating()));
        holder.txtBlank.setText(String.valueOf(stringList.get(position).getBlank()));
        DecimalFormat df = new DecimalFormat("#,###,###");
        holder.txtPrice.setText(df.format(stringList.get(position).getPrice()));
        holder.lnItemTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(activity, ListSeat.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", String.valueOf(stringList.get(position).get_id()));
                intent1.putExtra("add", bundle);
                activity.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        LinearLayout lnItemTrip;
        TextView txtTimeBegin,
        txtTimeFinish,
        txtFromProject,
        txtToProject,
        txtTitle,
        txtRate,
        txtBlank,
        txtPrice;
        public MyViewHodler(@NonNull View itemView){
            super(itemView);
            lnItemTrip  = itemView.findViewById(R.id.lnItemTrip);
            txtTimeBegin  = itemView.findViewById(R.id.txtTimeBegin);
            txtTimeFinish = itemView.findViewById(R.id.txtTimeFinish);
            txtFromProject = itemView.findViewById(R.id.txtFromProject);
            txtToProject = itemView.findViewById(R.id.txtToProject);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtRate = itemView.findViewById(R.id.txtRate);
            txtBlank = itemView.findViewById(R.id.txtBlank);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}