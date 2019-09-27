package com.example.pharmeasy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

public class pharmFeedback extends AppCompatActivity {
    EditText textInputName;
    EditText textInputEmail;
    EditText textinputMessage;
    Button buttonsubmit;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharm_feedback);


        textInputName=(EditText)findViewById(R.id.feedname);
        textInputEmail=(EditText)findViewById(R.id.feedmail);
        textinputMessage=(EditText)findViewById(R.id.feedback);
        buttonsubmit=(Button)findViewById(R.id.button_feed);

        dbHelper = new DBHelper(this);

        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();

                Toast.makeText(getApplicationContext(),"Successfully Submitted!",Toast.LENGTH_LONG).show();



            }
        });



    }


    private void addUser(){

        String name = textInputName.getText().toString().trim();
        String mail = textInputEmail.getText().toString().trim();
        String msg = textinputMessage.getText().toString().trim();


        dbHelper.addfeed(name,mail,msg);
    }


}
