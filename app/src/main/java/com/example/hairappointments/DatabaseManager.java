package com.example.hairappointments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {


    public DatabaseManager(Context context) {

        super(context, "AppointmentsDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = " create table AppointmentTable(";
        sql += "id integer primary key autoincrement, ";
        sql += "name text, date text, style text, price text)";

        db.execSQL(sql);

    }

    public void insert(String customerName, String dateBooked, String styleSelected, String priceOfStyle) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into AppointmentTable values(";
        sql += "null, '" + customerName + "', '" + dateBooked + "','" + styleSelected + "','" + priceOfStyle + "')";
        db.execSQL(sql);
        db.close();
    }
    public void updateByName(String name, String date, String style, String price){
        //given a name,date, style and price  it edits the name of the following items?
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update AppointmentTable set date = '"+date+"', style = '"+style+"', price ='"+price+"' ";
        sql+= "where name = '"+name+"'";
        db.execSQL(sql);
        db.close();

    }


    public ArrayList<String> getNames(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from AppointmentTable";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            list.add(name);
        }
        db.close();
        return list;
    }

    public String[] get(String appointmentName){

        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from AppointmentTable where name = '"+appointmentName+"'";
        Cursor cursor = db.rawQuery(sql, null);
        String[] entry = new String[4];
        if(cursor.moveToFirst()){
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            String style = cursor.getString(3);
            String price = cursor.getString(4);
            entry[0]=name;
            entry[1]=date;
            entry[2]=style;
            entry[3]=price;
        }
        db.close();
        return entry;
    }


    public void delete(String name){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from AppointmentTable where name ='"+name+"'";
        db.execSQL(sql);
        db.close();

    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}