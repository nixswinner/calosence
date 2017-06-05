package com.tergech.nixon.foodncalories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Tonui on 6/5/2017.
 */

public class ShowTotalCalo extends AppCompatActivity {
    private TextView tvcalo;
    private ImageButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcalo);
        tvcalo=(TextView)findViewById(R.id.tvcalo);
        btnAdd=(ImageButton)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvcalo.setText("Hello its working");
                Intent intent= new Intent(ShowTotalCalo.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
