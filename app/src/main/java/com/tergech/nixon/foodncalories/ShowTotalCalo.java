package com.tergech.nixon.foodncalories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tonui on 6/5/2017.
 */

public class ShowTotalCalo extends AppCompatActivity {
    private TextView tvcalo,tvDisplay;
    private ImageButton btnAdd;
    DBAdapter adapter;
    DBOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcalo);
        tvcalo=(TextView)findViewById(R.id.tvcalo);
        btnAdd=(ImageButton)findViewById(R.id.btnAdd);
        Button btnsave=(Button)findViewById(R.id.save) ;
        Button btnshow=(Button)findViewById(R.id.btnshow) ;
        //common common=new common();
       // int calo=common.retrieve();
        String omsg, date=getNow();
        String msg="2017-06-06";
        Date dd=stringToDate(msg);
        if (date.compareTo(msg)<0) {

            omsg="Its is not Equal";
        }
        else
        {
            omsg=" Equal";
        }

        tvDisplay=(TextView)findViewById(R.id.tvdisplay);
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new DBAdapter(ShowTotalCalo.this);
                int a=adapter.count();
                tvDisplay.setText("Test Data "+a);
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new DBAdapter(ShowTotalCalo.this);
                long val = adapter.insertDetails("20", getNow(),"Spagetti");
                //finish();
            }
        });
        tvcalo.setText("The time is  " +date +" "+omsg);
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
