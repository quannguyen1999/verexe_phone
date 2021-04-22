package com.example.verexe.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verexe.ListToLocationMain;
import com.example.verexe.R;
import com.example.verexe.model.Location;
import com.example.verexe.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class AdapterListToLocation extends RecyclerView.Adapter<AdapterListToLocation.MyViewHodler> {
    List<Location> stringList;
    Context context;
    private Activity activity;

    private List<Seat> seatList;
    private String idCoach;
    private Location fromLocatiion;

    public AdapterListToLocation(Context ct, List<Location> stringList, Activity activity, List<Seat> seatList
            , String idCoach, Location fromLocatiion) {
        context = ct;
        this.stringList = stringList;
        this.activity = activity;
        this.seatList = seatList;
        this.idCoach = idCoach;
        this.fromLocatiion = fromLocatiion;
    }

    @NonNull
    @Override
    public AdapterListToLocation.MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_list_from_location, parent, false);
        return new AdapterListToLocation.MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListToLocation.MyViewHodler holder, int position) {
        String fromHour = stringList.get(position).getHour() <= 9 ?
                "0" + stringList.get(position).getHour() :
                String.valueOf(stringList.get(position).getHour());
        String toHour = stringList.get(position).getHour() <= 9 ?
                "0" + stringList.get(position).getHour() :
                String.valueOf(stringList.get(position).getHour());
        String fromMinute = stringList.get(position).getMinute() <= 9 ?
                "0" + stringList.get(position).getMinute() :
                String.valueOf(stringList.get(position).getMinute());
        String toMinute = stringList.get(position).getMinute() <= 9 ?
                "0" + stringList.get(position).getMinute() :
                String.valueOf(stringList.get(position).getMinute());

        holder.txtTimeFromLocation.setText(fromHour + ":" + toHour);
        holder.txtLocation.setText(stringList.get(position).getNameProject());

        holder.lnItemFromLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ok");
//                Intent intent1 = new Intent(activity, ListToLocationMain.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("id", idCoach);
//                bundle.putParcelableArrayList("seat_order", (ArrayList<? extends Parcelable>) seatList);
//                bundle.putParcelable("fromLocation", (Parcelable) fromLocatiion);
//                bundle.putParcelable("toLocation", (Parcelable) stringList.get(position));
//                intent1.putExtra("add", bundle);
//                activity.startActivity(intent1);
            }
        });
//        holder.txtLocation.setText(stringList.get(position).getNameProject());
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        LinearLayout lnItemFromLocation;
        TextView txtTimeFromLocation;
        TextView txtLocation;
//        Button btnChoose;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            lnItemFromLocation = itemView.findViewById(R.id.lnItemFromLocation);
            txtTimeFromLocation = itemView.findViewById(R.id.txtTimeFromLocation);

//            txtTimeBegin  = itemView.findViewById(R.id.txtTimeFromLocation);

            txtLocation = itemView.findViewById(R.id.txtLocation);
//            btnChoose = itemView.findViewById(R.id.btnChoose);
        }
    }
//    public void filterList(ArrayList<String> filteredList) {
//        stringList = filteredList;
//        notifyDataSetChanged();
//    }
}
