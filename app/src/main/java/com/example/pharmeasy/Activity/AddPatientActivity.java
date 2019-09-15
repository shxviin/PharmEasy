package com.example.pharmeasy.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

public class AddPatientActivity extends AppCompatActivity {
    Button button_register;
    String name,addrs, desc,medi1,medi2,medi3,medi4,medi5,medi6,medi7,medi8;
    String phone;
    DBHelper dbHelper=new DBHelper(this) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        name = findViewById(R.id.txtpatient).toString();
        addrs=findViewById(R.id.address).toString();
        phone = findViewById(R.id.mobileNo).toString();
        desc= findViewById(R.id.diagnosis).toString();
        medi1=findViewById(R.id.med1).toString();
        medi2=findViewById(R.id.med2).toString();
        medi3=findViewById(R.id.med3).toString();
        medi4=findViewById(R.id.med4).toString();
        medi5=findViewById(R.id.med5).toString();
        medi6=findViewById(R.id.med6).toString();
        medi7=findViewById(R.id.med7).toString();
        medi8=findViewById(R.id.med8).toString();

        button_register=findViewById(R.id.btn_addPatient);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result= dbHelper.addPrescription(name,desc,addrs,phone,medi1,medi2,medi3,medi4,medi5,medi6,medi7,medi8);

                if (result == true){
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
