package com.tergech.nixon.foodncalories;

/**
 * Created by Tonui on 3/15/2017.
 */

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Tab_drinks extends Fragment {
    food_stuff food_stuff;
    String[] protein=food_stuff.drinks;
    String[] protein_calories_content=food_stuff.drinks_caloContent;
    ArrayAdapter<String> adapter;
    String[] outputStrArr;
    ListView listView;
    Button btnsubmit;
    food_stuff drinks;
    int calo=0;
    int arraysize;
    common common;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_drinks,container,false);
        listView=(ListView) v.findViewById(R.id.listview);
        btnsubmit=(Button) v.findViewById(R.id.btnSumit);
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_multichoice,protein);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(getActivity(),"You have selected  "+protein[pos],Toast.LENGTH_SHORT).show();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checked = listView.getCheckedItemPositions();
                ArrayList<String> selectedItems = new ArrayList<String>();
                for (int i = 0; i < checked.size(); i++) {
                    // Item position in adapter
                    int position = checked.keyAt(i);
                    // Add sport if it is checked i.e.) == TRUE!
                    if (checked.valueAt(i))
                    {
                        selectedItems.add(adapter.getItem(position));
                    }


                }
                 outputStrArr = new String[selectedItems.size()];
                int calo=0;
                //alert dialog
                for (int i = 0; i < selectedItems.size(); i++) {
                    outputStrArr[i] = selectedItems.get(i);

                    calo =calo+Integer.parseInt(protein_calories_content[i]);

                }
                validate(calo,"Drinks");



            }
        });




        return v;
    }
    public void alert(String title,String Message)
    {
        final EditText taskEditText = new EditText(getActivity());
        taskEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage("Please estimate the number cups of "+Message+" you have taken.")
                .setView(taskEditText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String no = String.valueOf(taskEditText.getText());
                        int Cups=Integer.parseInt(no);
                        Toast.makeText(getActivity(),"Cups "+Cups,Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
    }



    //method to check if no food was selected
    public void validate(int calories,String food_name)
    {

        if (calories==0)
        {
            Snackbar.make(getView(), "Please Select the food you took", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                    .setTitle("Confirm "+food_name+" Selected ")

                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getActivity(), "Being submitted shortly...."+"total calories is "+calo, Toast.LENGTH_SHORT).show();
                            //food take passed as string for logging purpose
                            String _date=common.getNow();
                            String fstr=common.ConvertArrayToString(outputStrArr,arraysize);
                            String calories=Integer.toString(calo);
                            Database db=new Database(getActivity());
                            //saving the confirmed food into the database
                            //db.save(calories,common.getNow(),fstr);
                            calo=0;

                        }
                    })
                    .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getActivity(), "You have cancled....you can select again", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setItems(outputStrArr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Toast.makeText(getActivity(), outputStrArr[which], Toast.LENGTH_SHORT).show();
                        }
                    });


            AlertDialog dialog = alertDialogBuilder.create();
            dialog.show();
            dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        }

    }}
