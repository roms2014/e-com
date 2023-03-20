package com.example.e_com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDataBase extends SQLiteOpenHelper {
    private static final String DATAВASE_NAМE = "my_database.db";
    private static final int DATAВASE_VERSION = 11;
    public static final String TABLE_NAME_tv_table = "tv_table";
    public static final String TABLE_NAME_category = "category";
    public static final String TABLE_NAME_tv_order = "tv_order";
    public static final String UID = "_id";
    public static final String UID2 = "_id";
    public static final String UIMG = "uimg";
    public static final String UTITLE = "utitle";
    public static final String UPRICE = "uprice";
    public static final String URESOLUTION = "uresolution";
    public static final String UBACKGROUND = "ubackground";
    public static final String UTEXT = "utext";
    public static final String UCATEGORY = "ucategory";
    public static final String UNAME = "uname";
    public static final String UORDER = "uorder";

    private static final String SQL_CREATE_TV_TABLE = "CREATE TABLE "
            + TABLE_NAME_tv_table + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UCATEGORY + " VARCHAR(255),"
            + UIMG + " VARCHAR(255), " + UTITLE + " VARCHAR(255), "
            + UPRICE + " VARCHAR(255), " + URESOLUTION + " VARCHAR(255), "
            + UBACKGROUND + " VARCHAR(255), " + UTEXT + " VARCHAR(255)); ";
    private static final String SQL_CREATE_CATEGORY = "CREATE TABLE "
            + TABLE_NAME_category + " (" + UID2 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UNAME + " VARCHAR(255));";
    private static final String SQL_CREATE_TV_ORDER = "CREATE TABLE "
            + TABLE_NAME_tv_order + " (" + UID2 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UORDER + " VARCHAR(255), " + UTITLE + " VARCHAR(255));";

    private static final String SQL_DELETE_TV_TABLE = "DROP TABLE IF EXISTS "
            + TABLE_NAME_tv_table;
    private static final String SQL_DELETE_TV_ORDER = "DROP TABLE IF EXISTS "
            + TABLE_NAME_tv_order;
    private static final String SQL_DELETE_CATEGORY = "DROP TABLE IF EXISTS "
            + TABLE_NAME_category;

    public MyDataBase(Context context){
        super(context, DATAВASE_NAМE, null, DATAВASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORY);
        sqLiteDatabase.execSQL(SQL_CREATE_TV_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TV_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w("LOG_TAG", "Upgrading DB from version "
                + i + " to version " + i1);
        sqLiteDatabase.execSQL(SQL_DELETE_TV_TABLE);
        sqLiteDatabase.execSQL(SQL_DELETE_TV_ORDER);
        sqLiteDatabase.execSQL(SQL_DELETE_CATEGORY);
        onCreate(sqLiteDatabase);
    }
}
