package com.tergech.nixon.foodncalories;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
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
    ListView food_listview;
    DBAdapter adapter;
    DBOpenHelper helper;
    ArrayAdapter<String> listadapter;
    Database db=new Database(ShowTotalCalo.this);
//adding floating button floatingActionButton
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcalo);
        tvcalo=(TextView)findViewById(R.id.tvcalo);
        btnAdd=(ImageButton)findViewById(R.id.btnAdd);
        Button btnsave=(Button)findViewById(R.id.save) ;
        Button btnprogress=(Button)findViewById(R.id.progress);
    /*    Button btnshow=(Button)findViewById(R.id.btnshow) ;*/
        tvDisplay=(TextView)findViewById(R.id.tvdisplay);
        tvfoodlog=(TextView)findViewById(R.id.tvfoodlog);
        tvfoodlog.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvcalo.setText("Hello its working");
                Intent intent= new Intent(ShowTotalCalo.this,MainActivity.class);
                startActivity(intent);
                ShowTotalCalo.this.finish();

            }
        });


        //displays the progress activity:
        btnprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShowTotalCalo.this,progress.class);
                startActivity(intent);
            }
        });
        //displays todays calories
        int todayscalo=getTodayCaloriesUptake(getNow());
        tvDisplay.setText(""+todayscalo);
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
            omsg=" ";
        }


    /*    btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               *//* adapter = new DBAdapter(ShowTotalCalo.this);
                //displays todays calories
                int a=db.getCalo(getNow());
                tvDisplay.setText("Test Data "+a);*//*
                showfood_log();
            }
        });*/
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //common.savedata("10",_date,"meat");
               // db.save("20",_date,"Meat");
                Toast.makeText(ShowTotalCalo.this, "There are " + db.count() + " records in the database", Toast.LENGTH_SHORT).show();
            }
        });
        btnsave.setVisibility(View.GONE);
        tvcalo.setText("" +_date +" "+omsg);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tvcalo.setText("Hello its working");
                Intent intent= new Intent(ShowTotalCalo.this,MainActivity.class);
                startActivity(intent);
                ShowTotalCalo.this.finish();
            }
        });
        TextView food_log=(TextView) findViewById(R.id.food_log);
        food_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showfood_log();
            }
        });


    }

    public void showfood_log()
    {
        //show the food log taken
        String myfood=db.getFoodTaken(getNow());
        if (myfood=="")
        {

            tvfoodlog.setText("You haven't take food today");
            Toast.makeText(ShowTotalCalo.this, "You haven't take food today \n Please add if you have", Toast.LENGTH_LONG).show();

        }
        else {
            //tvfoodlog.setText("You have taken the following today:\n "+myfood);
            String food_list[]=common.convertStringToArray(myfood); //convert string to array
            listadapter=new ArrayAdapter<String>(ShowTotalCalo.this,android.R.layout.simple_list_item_1,food_list);
            //food_listview=(ListView) findViewById(R.id.food_listview);
            // food_listview.setAdapter(listadapter);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                    .setTitle("List of Food Taken Today")

                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })

                    .setItems(food_list, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Toast.makeText(getActivity(), outputStrArr[which], Toast.LENGTH_SHORT).show();
                        }
                    });


            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
            dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

        tvcalo.setText(myfood);
        }
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
