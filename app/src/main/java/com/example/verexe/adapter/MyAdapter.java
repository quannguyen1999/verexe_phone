package com.example.verexe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verexe.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {

    List<String> stringList;
    Context context;

    public MyAdapter(Context ct,List<String> stringList){
        context = ct;
        this.stringList = stringList;
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
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        TextView nameProvince;
        public MyViewHodler(@NonNull View itemView){
            super(itemView);
            nameProvince = itemView.findViewById(R.id.nameProvince);
        }
    }
}
