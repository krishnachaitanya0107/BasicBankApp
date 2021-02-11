package com.example.basicbankapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankapp.ViewHolder.ClickListener;

import java.util.List;
public class CustomListAdapter extends RecyclerView.Adapter<ViewHolder> {

    CustomerListActivity UserList;
    List<Model> modelList;
    Context context;

    public CustomListAdapter(CustomerListActivity userList, List<Model> modelList) {
        this.UserList = userList;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ClickListener() {
            @Override
            public void onItemClick() {

            }

            @Override
            public void onItemClick(View view, int position) {
                UserList.nextActivity(position);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mName.setText(String.format(" %s ", modelList.get(position).getName()));
        holder.mPhonenumber.setText(String.format(" %s ", modelList.get(position).getPhoneno()));
        holder.mBalance.setText(String.format("Rs. %s /-", modelList.get(position).getBalance()));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
