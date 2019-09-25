package com.example.pharmeasy.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

public class entryActivity extends AppCompatActivity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        dbHelper = new DBHelper(this);

//        Toast.makeText(getApplicationContext(),type,Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {




                String type  = dbHelper.gettype();
                Intent launchIntent = new Intent();
                if(type.equals("Pharmacy")){
                    launchIntent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(launchIntent);
                }

                else if(type.equals("Hospital")){
                    launchIntent.setClass(getApplicationContext(), HospitalActivity.class);
                    startActivity(launchIntent);
                }
                else if(type.equals("Patient")){
                    launchIntent.setClass(getApplicationContext(), PatientsActivity.class);
                    startActivity(launchIntent);
                }
                else if(type.equals("")){
                    Intent loginIntent = new Intent(entryActivity.this,LoginActivity.class);
                    startActivity(loginIntent);
                }
                finish();
            }
        }, 4750);




    }





}
