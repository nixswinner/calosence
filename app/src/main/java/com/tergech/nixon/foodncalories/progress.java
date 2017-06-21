package com.tergech.nixon.foodncalories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by Tonui on 6/17/2017.
 */

public class progress extends AppCompatActivity {
    common common;
    String date=common.getNow();
    Database db=new Database(this);
    //graphs
    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_progress);


        BarChart barChart = (BarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> entries = new ArrayList <BarEntry>();
                entries.add(new BarEntry(4f, 0));
                entries.add(new BarEntry(8f, 1));
                entries.add(new BarEntry(6f, 2));
                entries.add(new BarEntry(12f, 3));
                entries.add(new BarEntry(18f, 4));
                entries.add(new BarEntry(9f, 5));
                entries.add(new BarEntry(9f, 6));

        BarDataSet dataset=new BarDataSet(entries,"Calories");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Monday");
        labels.add("Tuesday");
        labels.add("Wednesday");
        labels.add("Thursday");
        labels.add("Friday");
        labels.add("Saturday");
        labels.add("Sunday");

        //setting data to the chart



    }

}
