package com.example.verexe.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verexe.MainActivity;
import com.example.verexe.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler>{
    List<String> stringList;
    Context context;
    private Activity activity;

    public MyAdapter(Context ct,List<String> stringList,Activity activity){
        context = ct;
        this.stringList = stringList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.nameProvince.setText(stringList.get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(activity, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("value", stringList.get(position));
                intent1.putExtra("add", bundle);
                activity.setResult(Activity.RESULT_OK,intent1);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        TextView nameProvince;
        LinearLayout linearLayout;
        public MyViewHodler(@NonNull View itemView){
            super(itemView);
            nameProvince = itemView.findViewById(R.id.nameProvince);
            linearLayout = itemView.findViewById(R.id.lnItem);
        }
    }

    public void filterList(ArrayList<String> filteredList) {
        stringList = filteredList;
        notifyDataSetChanged();
    }
}
