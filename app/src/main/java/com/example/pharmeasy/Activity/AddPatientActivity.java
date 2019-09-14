package com.example.pharmeasy.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pharmeasy.R;

public class AddPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        Button button_register =findViewById(R.id.btn_addPatient);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_intent = new Intent( AddPatientActivity.this, PatientsActivity.class);
                startActivity(add_intent);
            }
        });
    }
}
