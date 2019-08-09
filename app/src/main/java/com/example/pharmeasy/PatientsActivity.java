package com.example.pharmeasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientsActivity extends AppCompatActivity {
    private  Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        Button button_addpatient =findViewById(R.id.addPatient);
        button_addpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_intent = new Intent( PatientsActivity.this, AddPatientActivity.class);
                startActivity(add_intent);
            }
        });
    }
}
