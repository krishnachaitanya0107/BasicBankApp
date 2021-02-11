package com.example.basicbankapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.basicbankapp.ViewHolder.ClickListener;

public class TransferHistoryAdapter extends RecyclerView.Adapter<ViewHolder> {

    TransferHistoryActivity HistoryList;
    List<Model> modelList;
    Context context;

    TextView mTrans_status;
    String redColorTag="#f40404";
    String greenColorTag="#008000";

    public TransferHistoryAdapter(TransferHistoryActivity historyList, List<Model> modelList) {
        this.HistoryList = historyList;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transfer_history_list, parent, false);

        mTrans_status = itemView.findViewById(R.id.transaction_status);

        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ClickListener() {
            @Override
            public void onItemClick() {

            }

            @Override
            public void onItemClick(View view, int position) {

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mName1.setText(String.format("%s -> %s", modelList.get(position).getName1(), modelList.get(position).getName2()));
        holder.mBalance.setText(String.format("Rs. %s /-", modelList.get(position).getBalance()));
        holder.mDate.setText(modelList.get(position).getDate());
        holder.mTrans_status.setText(modelList.get(position).getTransaction_status());

        if(modelList.get(position).getTransaction_status().equals("Failed")){
            holder.mTrans_status.setTextColor(Color.parseColor(redColorTag));
        }else{
            holder.mTrans_status.setTextColor(Color.parseColor(greenColorTag));
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
