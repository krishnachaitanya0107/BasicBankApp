package com.example.basicbankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9999999990,'Benedict',1234.00,'BenedictCumberbatch@gmail.com','1234567890','A1234')");
        db.execSQL("insert into user_table values(9999999991,'Sachi',300.56,'sachi@gmail.com','1234567891','B1235')");
        db.execSQL("insert into user_table values(9999999992,'Suraj',17777.45,'Suraj3@gmail.com','1234567892','C1236')");
        db.execSQL("insert into user_table values(9999999993,'sowmya',1600.97,'sowmya4@gmail.com','1234567893','D1237')");
        db.execSQL("insert into user_table values(9999999994,'jason',2460.49,'jason1234@gmail.com','1234567894','E1238')");
        db.execSQL("insert into user_table values(9999999995,'tatagatha',1945.86,'tatagatha@gmail.com','1234567895','F1239')");
        db.execSQL("insert into user_table values(9999999996,'Quill',566.99,'peterquill@gmail.com','1234567896','G1240')");
        db.execSQL("insert into user_table values(9999999997,'lucas',654.01,'lucas8@gmail.com','1234567897','H1241')");
        db.execSQL("insert into user_table values(9999999998,'david',876.49,'davidwarner@gmail.com','1234567898','I1242')");
        db.execSQL("insert into user_table values(9999999999,'Pandey',86666.80,'IamPandey@gmail.com','1234567899','J1243')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }
    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readTransferData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table order by transactionid desc", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

}

