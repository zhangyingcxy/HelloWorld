package com.swufe.helloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;
    private  String TBNAME;

    public DBManager(Context context) {
        this.dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }
    public void addAll(List<RateItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for(RateItem item:list){
            ContentValues values = new ContentValues();
            values.put("curname",item.getCurName());
            values.put("currate",item.getCurRate());
            db.insert(TBNAME,null,values);
        }
        db.close();

    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();

    }

    public List<RateItem> listAll(){
        List<RateItem>  ratelist = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,null,null,null,
                null,null);
        if(cursor != null){
            ratelist = new ArrayList<RateItem>();
            while (cursor.moveToNext()){
                RateItem item = new RateItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurName(cursor.getString(cursor.getColumnIndex("curname")));
                item.setCurRate(cursor.getString(cursor.getColumnIndex("currate")));
                ratelist.add(item);
            }
            cursor.close();
        }
        db.close();
        return ratelist;
    }

}
