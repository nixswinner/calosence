package com.tergech.nixon.foodncalories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

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
    //BarChart chart;
    private SeekBar mSeekBarX, mSeekBarY;

    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progress);


        chart = (BarChart) findViewById(R.id.chart);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        AddValuesToBARENTRY();

        AddValuesToBarEntryLabels();

        Bardataset = new BarDataSet(BARENTRY, "Calories Uptake");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);

        chart.animateY(3000);
        chart.setDescription("");
    }

    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(200, 0));
        BARENTRY.add(new BarEntry(400, 1));
        BARENTRY.add(new BarEntry(600, 2));
        BARENTRY.add(new BarEntry(100, 3));
        BARENTRY.add(new BarEntry(400, 4));
        BARENTRY.add(new BarEntry(550, 5));
        BARENTRY.add(new BarEntry(670, 6));

    }

    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add("Mon");
        BarEntryLabels.add("Tue");
        BarEntryLabels.add("Wed");
        BarEntryLabels.add("Thur");
        BarEntryLabels.add("Fri");
        BarEntryLabels.add("Sat");
        BarEntryLabels.add("Sun");

    }

}
