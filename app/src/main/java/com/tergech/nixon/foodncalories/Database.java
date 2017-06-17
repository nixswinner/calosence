package com.tergech.nixon.foodncalories;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public Database(Context context) {
		super(context, "fcalorie_db", null, 1);// modify here
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// modify this query to create your own table
		/*String sql = "CREATE TABLE  students"//IF NOT EXISTS,students
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " name TEXT NOT NULL, " + " email TEXT NOT NULL,"
				+ " course TEXT NOT NULL," + " age TEXT NOT NULL"+ "phone TEXT NOT NULL)";//,"+ "phone TEXT NOT NULL*/
		String sql = "CREATE TABLE  sasa"//IF NOT EXISTS,students
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " calories TEXT NOT NULL, " + " date TEXT NOT NULL,"
				 + " food TEXT NOT NULL)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*String sql = "DROP TABLE IF EXISTS students";// modify to suit your table //students*/
		String sql = "DROP TABLE IF EXISTS sasa";
		db.execSQL(sql);
	}
	//getting calories based on now
	public int getCalo(String tdate)
	{
		int total_calo=0;
		SQLiteDatabase db = this.getReadableDatabase();
		String sql ="SELECT * FROM sasa WHERE date='"+tdate+"'";// modify here to reflect your table
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(1);
				total_calo+=Integer.parseInt(name);
			} while (cursor.moveToNext()); // modify here
		}
		return total_calo;

	}
	//return for the graph
	public int getCalo_for_graph(String tdate)
	{
		int total_calo=0;
		SQLiteDatabase db = this.getReadableDatabase();
		String sql ="SELECT * FROM sasa WHERE date='"+tdate+"'";// modify here to reflect your table
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(1);
				total_calo=Integer.parseInt(name);
			} while (cursor.moveToNext()); // modify here
		}
		return total_calo;

	}

    public boolean check_if_end_calo(String tdate)
    {
        int total_calo=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql ="SELECT * FROM sasa WHERE date='"+tdate+"'";// modify here to reflect your table
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                return true;
            } while (cursor.moveToNext()); // modify here
        }
        return false;

    }

	//getting calories based on now
	public String getFoodTaken(String tdate)
	{
		String food_name="";
		SQLiteDatabase db = this.getReadableDatabase();
		//String sql ="SELECT * FROM sasa WHERE date='"+tdate+"'";// modify here to reflect your table
		String sql ="SELECT * FROM sasa WHERE date='"+tdate+"'";// modify here to reflect your table
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			do {
				 food_name = cursor.getString(3);
                food_name=food_name+food_name;

			} while (cursor.moveToNext()); // modify here
		}
		return food_name;

	}

//	Saving data in the database
	public void save(String calories, String _date, String food)// Modify here to

	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("calories", calories);// modify here
		values.put("date", _date);// modify here
		values.put("food", food);// modify here
		db.insert("sasa", null, values);// modify here students
		db.close();
	}

	/**
	 * Fetches all records from the database and return an arraylist of type
	 * specified
	 */


//	Counts the records in the database
	public int count() {
		SQLiteDatabase db = this.getReadableDatabase();
		//String sql = "SELECT id,name,course FROM students";// modify here
		String sql = "SELECT * FROM sasa";
		Cursor cursor = db.rawQuery(sql, null);
		return cursor.getCount();
	}

	/*
	 * Deletes a record with specified ID
	 */
	public void delete(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("users", "id=" + id, null); // Modify
		db.close();
	}

	/*
	 * Updates a record with specified fields
	 */
	public void update(String names, String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "UPDATE users set names='" + names + "' WHERE id=" + id;
		db.rawQuery(query, null);
		db.close();
	}
}
