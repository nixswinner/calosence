package com.tergech.nixon.foodncalories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tonui on 6/5/2017.
 */

public class ShowTotalCalo extends AppCompatActivity {
    common common;
    private TextView tvcalo,tvDisplay,tvfoodlog;
    private ImageButton btnAdd;
    DBAdapter adapter;
    DBOpenHelper helper;
    Database db=new Database(ShowTotalCalo.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcalo);
        tvcalo=(TextView)findViewById(R.id.tvcalo);
        btnAdd=(ImageButton)findViewById(R.id.btnAdd);
        Button btnsave=(Button)findViewById(R.id.save) ;
        Button btnshow=(Button)findViewById(R.id.btnshow) ;
        tvDisplay=(TextView)findViewById(R.id.tvdisplay);
        tvfoodlog=(TextView)findViewById(R.id.tvfoodlog);

        //show the food log taken
        String myfood=db.getFoodTaken(getNow());
        if (myfood=="")
        {
            tvfoodlog.setText("You haven't take food today");
        }
        else {
            tvfoodlog.setText("You have taken the following today:\n "+myfood);
        }
        //displays todays calories
        int todayscalo=getTodayCaloriesUptake(getNow());
        tvDisplay.setText("Todays Calories Uptake "+todayscalo);
        /*String todayfood=db.getFoodTaken(getNow());
        tvDisplay.setText("Todays Food Uptake "+todayfood);*/
        final String omsg, _date=getNow();
        String msg="2017-06-06";
        Date dd=stringToDate(msg);
        if (_date.compareTo(msg)<0) {

            omsg="Its is not Equal";
        }
        else
        {
            omsg=" Equal";
        }


        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new DBAdapter(ShowTotalCalo.this);
                //displays todays calories
                int a=db.getCalo(getNow());
                tvDisplay.setText("Test Data "+a);
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //common.savedata("10",_date,"meat");
               // db.save("20",_date,"Meat");
                Toast.makeText(ShowTotalCalo.this, "There are " + db.count() + " records in the database", Toast.LENGTH_SHORT).show();
            }
        });
        tvcalo.setText("Today is  " +_date +" "+omsg);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvcalo.setText("Hello its working");
                Intent intent= new Intent(ShowTotalCalo.this,MainActivity.class);
                startActivity(intent);
                ShowTotalCalo.this.finish();
            }
        });

    }
    //this method gets the calories uptake
    private int getTodayCaloriesUptake(String leo)
    {
        int calo=0;
        calo=db.getCalo(leo);
        return calo;

    }
    private String getNow(){
        // set the format to sql date time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    private Date stringToDate(String d)
    {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date stringDate = simpledateformat.parse(d,pos);
        return stringDate;
    }
}
