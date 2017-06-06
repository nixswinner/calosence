package com.tergech.nixon.foodncalories;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tonui on 6/6/2017.
 */

public class common extends Activity{
    public SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
    public void savedata(int calo)
    {
        // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
       /* editor.putBoolean("key_name", true); // Storing boolean - true/false
        editor.putString("key_name", "string value"); // Storing string
        editor.putInt("key_name", 0); // Storing integer
        editor.putFloat("key_name", "float value"); // Storing float
        editor.putLong("key_name", "long value"); // Storing long*/
        editor.putInt("calories", calo); // Storing integer
        editor.commit(); // commit changes
    }

    public int retrieve()
    {
        SharedPreferences sharedpreferences = getSharedPreferences("MyPref",
                Context.MODE_PRIVATE);
       /* pref.getString("key_name", null); // getting String
        pref.getInt("key_name", null); // getting Integer
        pref.getFloat("key_name", null); // getting Float
        pref.getLong("key_name", null); // getting Long
        pref.getBoolean("key_name", null); // getting boolean */
       return sharedpreferences.getInt("calories",0);
    }

}
