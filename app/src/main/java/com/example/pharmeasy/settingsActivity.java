package com.example.pharmeasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridView;

public class settingsActivity extends AppCompatActivity {

    CardView editInfoGrid;
    CardView UpdatePwGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editInfoGrid = (CardView)findViewById(R.id.grid_editinfo);
        UpdatePwGrid = (CardView)findViewById(R.id.grid_updatePw);


        editInfoGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(settingsActivity.this,editinfoActivity.class);
                startActivity(registerIntent);
            }
        });

        UpdatePwGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(settingsActivity.this,updatePasswordActivity.class);
                startActivity(registerIntent);
            }
        });



    }






}
