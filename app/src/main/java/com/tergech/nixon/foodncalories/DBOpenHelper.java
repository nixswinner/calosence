package com.tergech.nixon.foodncalories;

/**
 * Created by Tonui on 5/25/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FOOD_DB";
    public static final String TABLE_NAME = "FOOD_TABLE";
    public static final int VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String CALORIES = "F_NAME";
    public static final String FDOOD = "L_NAME";
    public static final String DATE = "DATE";
    public static final String SCRIPT = "create table " + TABLE_NAME + " ("
            + KEY_ID + " integer primary key autoincrement, " + CALORIES
            + " text not null, " +DATE+"text not null, "+ FDOOD + " text not null );";

    public DBOpenHelper(Context context, String name,
                        CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }

}

