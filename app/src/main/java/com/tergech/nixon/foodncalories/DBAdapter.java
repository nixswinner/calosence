package com.tergech.nixon.foodncalories;

/**
 * Created by Tonui on 5/25/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
    SQLiteDatabase database_ob;
    DBOpenHelper openHelper_ob;
    Context context;

    public DBAdapter(Context c) {
        context = c;
    }

    public DBAdapter opnToRead() {
        openHelper_ob = new DBOpenHelper(context,
                DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.VERSION);
        database_ob = openHelper_ob.getReadableDatabase();
        return this;

    }

    public DBAdapter opnToWrite() {
        openHelper_ob = new DBOpenHelper(context,
                DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.VERSION);
        database_ob = openHelper_ob.getWritableDatabase();
        return this;

    }
    //	Saving data in the database
    public void save(String calories, String _date, String food)// Modify here to

    {
        opnToWrite();
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CALORIES, calories);// modify here
        values.put(DBOpenHelper.DATE, _date);// modify here
        values.put(DBOpenHelper.FDOOD, food); //Modify Here
        database_ob.insert(DBOpenHelper.TABLE_NAME, null, values);// modify here
        Close();
    }

    public void Close() {
        database_ob.close();
    }

    public long insertDetails(String calorie,String _Date,String food ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.CALORIES, calorie);
        contentValues.put(DBOpenHelper.DATE, _Date);
        contentValues.put(DBOpenHelper.FDOOD, food);
        opnToWrite();
        long val = database_ob.insert(DBOpenHelper.TABLE_NAME, null,
                contentValues);
        Close();
        return val;

    }

    public Cursor queryName() {
        String[] cols = {DBOpenHelper.KEY_ID, DBOpenHelper.CALORIES,
                DBOpenHelper.FDOOD};
        opnToWrite();
        Cursor c = database_ob.query(DBOpenHelper.TABLE_NAME, cols, null,
                null, null, null, null);

        return c;

    }
    public Cursor queryOneName(int nameId) {
        String[] cols = {DBOpenHelper.KEY_ID, DBOpenHelper.CALORIES,
                DBOpenHelper.FDOOD};
        opnToWrite();
        Cursor c = database_ob.query(DBOpenHelper.TABLE_NAME, cols,
                DBOpenHelper.KEY_ID + "=" + nameId, null, null, null, null);

        return c;

    }
    public Cursor queryAll(int nameId) {
        String[] cols = {DBOpenHelper.KEY_ID, DBOpenHelper.CALORIES,
                DBOpenHelper.FDOOD};
        opnToWrite();
        Cursor c = database_ob.query(DBOpenHelper.TABLE_NAME, cols,
                DBOpenHelper.KEY_ID + "=" + nameId, null, null, null, null);

        return c;

    }

    public long updateldetail(int rowId, String fname, String lname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.CALORIES, fname);
        contentValues.put(DBOpenHelper.FDOOD, lname);
        opnToWrite();
        long val = database_ob.update(DBOpenHelper.TABLE_NAME, contentValues,
                DBOpenHelper.KEY_ID + "=" + rowId, null);
        Close();
        return val;
    }

    public int deletOneRecord(int rowId) {
        // TODO Auto-generated method stub
        opnToWrite();
        int val = database_ob.delete(DBOpenHelper.TABLE_NAME,
                DBOpenHelper.KEY_ID + "=" + rowId, null);
        Close();
        return val;
    }
    public int getCalo()
    {
        int total_calo=0;
        //SQLiteDatabase db = this.getReadableDatabase();
        opnToRead();
        String sql = "SELECT * FROM FOOD_TABLE";// modify here to reflect your table
        Cursor cursor = database_ob.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                total_calo+=Integer.parseInt(name);
            } while (cursor.moveToNext()); // modify here
        }
        return total_calo;

    }

    public int count() {
        opnToRead();
        String sql = "SELECT * FROM FOOD_TABLE";// modify here
        Cursor cursor = database_ob.rawQuery(sql, null);
        return cursor.getCount();
    }

}


