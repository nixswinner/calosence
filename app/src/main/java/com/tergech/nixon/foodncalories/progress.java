package com.tergech.nixon.foodncalories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.BarChart;

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

        int calo=db.getCalo_for_graph(date);
        boolean value=db.check_if_end_calo(date);
        int[] calo_array={};
        while(value=false)
        {
            int i=0;
            calo_array[0]=calo;
            i++;
            //int x= Arrays.asList(calo_array).lastIndexOf(outputStrArr[i]);
        }
     /*   GraphView graph = (GraphView)findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(0, calo),
                new DataPoint(1, 3),
                new DataPoint(2, 6),
                new DataPoint(0, 1),
                new DataPoint(1, 3),
                new DataPoint(2, 6),

        });
        // series2.setColor(Color.parseColor("FC07D7"));
        graph.addSeries(series2);*/

        //*/ series2.setColor(Color.parseColor("FC07D7"));
       // graph.addSeries(series2);

    }

}
