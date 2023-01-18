package com.example.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper{

private static final String DATABASE_NAME = "biodatadiri.db";
private static final  int DATABASE_VERSION = 1;
public DataHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        }

@Override
public void onCreate(SQLiteDatabase db) {
        //sqLiteDatabase

        String sql = "create table biodata(no integer primary key, nama text null, tgl text null, jk text null,alamat text null,image blob null);";
        Log.d ("Data", "onCreate:"+ sql);
        db.execSQL(sql);
        }

@Override
public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        //sqLiteDatabase, int i, int i1

        }
 }

