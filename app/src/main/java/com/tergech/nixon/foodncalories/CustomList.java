package com.tergech.nixon.foodncalories;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Tonui on 6/17/2017.
 */


public class CustomList extends ArrayAdapter<String> {
    private String[] ids;
    private String[] calo;
    private String[] date;
    private Activity context;


    public CustomList(Activity context, String[] ids, String[] calo, String[] date) {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.ids = ids;
        this.calo = calo;
        this.date = date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView txtcalories = (TextView) listViewItem.findViewById(R.id.textCal);
        TextView txtdate = (TextView) listViewItem.findViewById(R.id.txtdate);

        textViewId.setText(ids[position]);
        txtcalories.setText(calo[position]);
        txtdate.setText(date[position]);

        return listViewItem;
    }
}
