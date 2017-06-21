package com.tergech.nixon.foodncalories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 9/22/2015.
 */
public class ParseJSON {
    public static String[] ids;
    public static String[] calories;
    public static String[] date;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "id";
    public  final String KEY_CALORIES = "calories";
    public  final String KEY_DATE = "date";
    //public static final String KEY_EMAIL = "email";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            calories = new String[users.length()];
            date = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                calories[i] = jo.getString(KEY_CALORIES);
                date[i] = jo.getString(KEY_DATE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}