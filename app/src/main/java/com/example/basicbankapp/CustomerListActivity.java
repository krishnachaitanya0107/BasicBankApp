package com.example.basicbankapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {
    List<Model> modelList_showlist = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomListAdapter adapter;

    String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        showData();
    }

    private void showData() {
        modelList_showlist.clear();
        Cursor cursor = new DatabaseHelper(this).readalldata();
        while(cursor.moveToNext()){
            String balancefromdb = cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(0), cursor.getString(1), price);
            modelList_showlist.add(model);
        }

        adapter = new CustomListAdapter(CustomerListActivity.this, modelList_showlist);
        mRecyclerView.setAdapter(adapter);
    }

    public void nextActivity(int position) {
        phonenumber = modelList_showlist.get(position).getPhoneno();
        Intent intent = new Intent(CustomerListActivity.this, CustomerDataActivity.class);
        intent.putExtra("phonenumber",phonenumber);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_transaction_history){
            startActivity(new Intent(CustomerListActivity.this, TransferHistoryActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent home_intent=new Intent(CustomerListActivity.this,MainActivity.class);
        startActivity(home_intent);
    }
}