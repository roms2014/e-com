package com.example.e_com;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBwork {
    MyDataBase mydb;
    SQLiteDatabase sqdb;
    Context context;

    public DBwork(Context context) {
        this.context = context;
    }

    public   void openConnection(){
        mydb = new MyDataBase(context);
        sqdb = mydb.getWritableDatabase();
    }

    public void closeConnection(){
        sqdb.close();
        mydb.close();
    }

    public void dbInsert_tv_table(String values){
        String insertQuery = "INSERT INTO " +
                MyDataBase.TABLE_NAME_tv_table + " ("
                + MyDataBase.UCATEGORY + "," + MyDataBase.UIMG + ", "
                + MyDataBase.UTITLE + ", " + MyDataBase.UPRICE + ", "
                + MyDataBase.URESOLUTION + ", " + MyDataBase.UBACKGROUND + ", "
                + MyDataBase.UTEXT+ ") VALUES (" + values + ")";
        sqdb.execSQL(insertQuery);
    }
    public boolean checkTable(String table_name){
        Cursor cursor = sqdb.rawQuery(
                "SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?",
                new String[] {"table", table_name}
        );
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
    public void dbInsert_category(String values){
        String insertQuery = "INSERT INTO " +
                MyDataBase.TABLE_NAME_category + " ("
                + MyDataBase.UNAME+ ") VALUES (" + values + ")";
        sqdb.execSQL(insertQuery);
    }
    public void dbInsert_tv_order(String values){
        String insertQuery = "INSERT INTO " +
                MyDataBase.TABLE_NAME_tv_order + " ("
                + MyDataBase.UORDER + "," + MyDataBase.UTITLE +
                ") VALUES (" + values + ")";
        sqdb.execSQL(insertQuery);
    }

    public List<String> dbSelect_tv_table(){
        Cursor cursor = sqdb.query(MyDataBase.TABLE_NAME_tv_table,new String[]{
                        MyDataBase.UID,MyDataBase.UCATEGORY,MyDataBase.UIMG, MyDataBase.UTITLE,
                        MyDataBase.UPRICE, MyDataBase.URESOLUTION, MyDataBase.UBACKGROUND,
                        MyDataBase.UTEXT},
                null,
                null,
                null,
                null,
                null
        );
        List<String> listReturn = new ArrayList<>();
        while(cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(MyDataBase.UID));
            @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(MyDataBase.UCATEGORY));
            @SuppressLint("Range") String img = cursor.getString(cursor.getColumnIndex(MyDataBase.UIMG));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(MyDataBase.UTITLE));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(MyDataBase.UPRICE));
            @SuppressLint("Range") String resolution = cursor.getString(cursor.getColumnIndex(MyDataBase.URESOLUTION));
            @SuppressLint("Range") String background = cursor.getString(cursor.getColumnIndex(MyDataBase.UBACKGROUND));
            @SuppressLint("Range") String text = cursor.getString(cursor.getColumnIndex(MyDataBase.UTEXT));
            listReturn.add(Integer.toString(id) + "`" + category+ "`" + img+ "`" + title
                    + "`" + price + "`" + resolution + "`" + background + "`" + text);
        }
        cursor.close();
        return listReturn;
    }

    public List<String> dbSelect_category(){
        Cursor cursor = sqdb.query(MyDataBase.TABLE_NAME_category,new String[]{
                        MyDataBase.UNAME},
                null,
                null,
                null,
                null,
                null
        );
        List<String> listReturn = new ArrayList<>();
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(MyDataBase.UNAME));
            listReturn.add(name);
        }
        cursor.close();
        return listReturn;
    }

}
