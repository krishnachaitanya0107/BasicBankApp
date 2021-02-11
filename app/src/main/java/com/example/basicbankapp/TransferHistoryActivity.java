package com.example.basicbankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class TransferHistoryActivity extends AppCompatActivity {

    List<Model> modelList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    TransferHistoryAdapter adapter;

    TextView history_empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_history);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        history_empty = findViewById(R.id.empty_text);


        showData();
    }



    private void showData() {
        modelList.clear();
        Cursor cursor = new com.example.basicbankapp.DatabaseHelper(this).readTransferData();

        while (cursor.moveToNext()) {
            String balancefromdb = cursor.getString(4);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(2), cursor.getString(3), price, cursor.getString(1), cursor.getString(5));
            modelList.add(model);
        }

        adapter = new TransferHistoryAdapter(TransferHistoryActivity.this, modelList);
        mRecyclerView.setAdapter(adapter);

        if(modelList.size() == 0){
            history_empty.setVisibility(View.VISIBLE);
        }

    }

}