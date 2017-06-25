package com.tergech.nixon.foodncalories;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tonui on 6/6/2017.
 */

public class common extends Activity {
    public static  String strSeparator="_,_";
    Context context;
    Database db=new Database(context);
    public static final String KEY_CALORIES = "calories";
    public static final String KEY_DATE = "date";
    public static final String KEY_FOOD = "food";
    private static final String REGISTER_URL = "http://nixontonui.net16.net/MyDB/volley.php";

    //saving data to online db

    public void save_to_db(String calories, String _date,  String food){
        final String calo=calories;
        final String _dt=_date;
        final  String fd=food;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_CALORIES,calo);
                params.put(KEY_DATE,_dt);
                params.put(KEY_FOOD, fd);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(common.this);
        requestQueue.add(stringRequest);
    }

    //saving to the logs to the db
    public static void savedata(String calo,String _date,String food)
    {
    /*    Context context;
        Database db=new Database(context);
        db.save(calo,_date,food);*/
       // db.save("10",_date,"Meat");

    }
/*public static int indexOfArray(String input,String[]array)
{
    for(int i=0;i<array.length();i++)
    {

    }
}*/
    //method to get the current date
    public static String getNow(){
        // set the format to sql date time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
//converting array to string
public static String ConvertArrayToString(String[] array,int size)
{

    String str="";
    for (int i=0;i<size;i++)
    {
        str=str+array[i];
        if (i<size-1)
        {
            str=str+strSeparator;
        }
    }
    return str;
}
//converting string to array
    public static String[] convertStringToArray(String str)
    {
        String[] arr=str.split(strSeparator);
        return arr;
    }



    //methods handling the food storage and calories







}
