package com.tergech.nixon.foodncalories;

import android.app.Activity;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tonui on 6/6/2017.
 */

public class common extends Activity {
    public static  String strSeparator="_,_";
    Context context;
    Database db=new Database(context);
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


}
