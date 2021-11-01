package com.example.slide6_class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class mapDAO {
    private DataBase dataBase;

    public mapDAO(Context context) {
        dataBase = new DataBase(context);
    }
    public long insertMAPS(mapModel mapModel){
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", mapModel.getID());
        contentValues.put("NAME", mapModel.getNameStore());
        contentValues.put("VIDO", mapModel.getKinhDo());
        contentValues.put("KINHDO", mapModel.getViDo());
        return sqLiteDatabase.insert("MAPS",null,contentValues);
    }
    public int deleteMaps(String id){
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        return sqLiteDatabase.delete("MAPS", "ID=?",new String[]{id});
    }
    public int updateMaps(mapModel mapModel){
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", mapModel.getNameStore());
        contentValues.put("VIDO", mapModel.getKinhDo());
        contentValues.put("KINHDO", mapModel.getViDo());
        return sqLiteDatabase.update("LoaiSach",contentValues,"maLoai=?",new String[]{String.valueOf(mapModel.getID())});
    }
    public List<mapModel> getAllsach(){
        List<mapModel> sachModels = new ArrayList<>();

        String sach = "SELECT * FROM Sach";
        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sach,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String ID = cursor.getString(cursor.getColumnIndex("ID"));
                String Name = cursor.getString(cursor.getColumnIndex("NAME"));
                String ViDo = cursor.getString(cursor.getColumnIndex("VIDO"));
                String KinhDo = cursor.getString(cursor.getColumnIndex("KINHDO"));
                mapModel sachModel = new mapModel(Integer.parseInt(ID),Name,ViDo,KinhDo);
                sachModels.add(sachModel);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return sachModels;
    }
}
