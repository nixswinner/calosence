package com.tergech.nixon.foodncalories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tonui on 6/17/2017.
 */

public class get_json extends AppCompatActivity  {
    public static final String JSON_URL = "http://nixontonui.net16.net/MyDB/fetch.php";
    private Button buttonGet;
    TextView txttest;
    String[] _ids={"1","3","5","6"},calo={"45","83","775","86"},dates={"3404","7809","2030","2017"};

    //.........................
    TextView output ;
    String loginURL="http://nixontonui.net16.net/MyDB/fetch.php";
    String data = "";
    //.........................

    //RequestQueue requestQueue;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_json);
        buttonGet=(Button)findViewById(R.id.buttonGet);
        listView=(ListView)findViewById(R.id.listView);
        txttest=(TextView)findViewById(R.id.txttest);



        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   //sendRequest();
                    //Toast.makeText(get_json.this,"Heloooo",Toast.LENGTH_LONG).show();
                   get_data();
                }
                catch (Exception ex){
                    Toast.makeText(get_json.this,"Error: "+ex,Toast.LENGTH_LONG).show();
                }
                //get_data();
            }
        });

    }

   public void get_data()
   {
       RequestQueue requestQueue = Volley.newRequestQueue(this);
       JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, loginURL, null,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {

                       try{

                           JSONArray ja = response.getJSONArray("result");

                           for(int i=0; i < ja.length(); i++){

                               JSONObject jsonObject = ja.getJSONObject(i);

                               // int id = Integer.parseInt(jsonObject.optString("id").toString());
                               String id = jsonObject.optString ("id");
                               String calories = jsonObject.optString ("calories");

                               data += "Food Number "+(i+1)+" \n id= "+id  +" \n calories= "+ calories +" \n\n\n\n ";
                           }

                           txttest.setText(data);
                       }catch(JSONException e){
                           e.printStackTrace();
                       Toast.makeText(get_json.this,"Error is: "+e,Toast.LENGTH_LONG).show();
                       }
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Log.e("Volley","Error");
                       Toast.makeText(get_json.this,"Volley Error is: "+error,Toast.LENGTH_LONG).show();
                   }
               }
       );
       requestQueue.add(jor);
   }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                                            }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(get_json.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        parse_json(json);
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

        try {
            //txttest.setText(ParseJSON.calo[1]);
            CustomList cl = new CustomList(this, ParseJSON.ids,ParseJSON.calories,ParseJSON.date);
           // CustomList cl = new CustomList(this, _ids,calo,dates);
            listView.setAdapter(cl);
        }catch (Exception ex)
        {
            Toast.makeText(get_json.this,"Error::"+ex, Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(get_json.this,"hello ....", Toast.LENGTH_LONG).show();
    }


    //my json parse
    public void parse_json(String json_data)
    {

        String OutputData = "";
        JSONObject jsonResponse;

        try {

            /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
            jsonResponse = new JSONObject(json_data);

            /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
            /*******  Returns null otherwise.  *******/
            JSONArray jsonMainNode = jsonResponse.optJSONArray("result");

            /*********** Process each JSON Node ************/

            int lengthJsonArr = jsonMainNode.length();

            for(int i=0; i < lengthJsonArr; i++)
            {
                /****** Get Object for each JSON node.***********/
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                /******* Fetch node values **********/
                //int id        = Integer.parseInt(jsonChildNode.optString("id").toString());
                String id   = jsonChildNode.optString("id").toString();
                String calories = jsonChildNode.optString("calories").toString();


                /*OutputData += "Node : \n\n     "+ id +" | "
                        + calories +" | ";*/
                OutputData="hhh";
                //Log.i("JSON parse", song_name);
            }

            /************ Show Output on screen/activity **********/

            txttest.setText(OutputData );
            //Toast.makeText(this,"Working till here ",Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {

           Toast.makeText(this,"Error "+e,Toast.LENGTH_LONG).show();
        }

    }
    }



