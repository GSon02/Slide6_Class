package com.example.slide6_class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super(context, "mapdatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableMap = "CREATE TABLE MAPS(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " NAME TEXT,"+
                " VIDO TEXT, "+
                " KINHDO TEXT)";
        sqLiteDatabase.execSQL(createTableMap);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
