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
    EditText name,addrs, diag, pres ;
    EditText phone;
    DBHelper dbHelper=new DBHelper(this) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        name = findViewById(R.id.txtpatient);
        addrs=findViewById(R.id.txtaddress);
        phone = findViewById(R.id.mobileNo);
        diag= findViewById(R.id.txtdiagnosis);
        pres = findViewById(R.id.txtPrescription);

        button_register=findViewById(R.id.btn_addPatient);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameIsfull()==true && addressIsfull()== true && mobileIsfull() == true && ValidatePhoneNo()==true && diagnosisIsfull() == true && prescriptionIsfull() == true){
                    addPrescription();
                    Intent patientList = new Intent(AddPatientActivity.this,PatientsActivity.class);
                    startActivity(patientList);
                }

            }
        });
    }

    private void addPrescription(){
      String pname= name.getText().toString().trim();
      String paddress= addrs.getText().toString().trim();
      String pphone= phone.getText().toString().trim();
      String pdiagnosis= diag.getText().toString().trim();
      String pprescription= pres.getText().toString().trim();

        long result2 = dbHelper.addOrder(pname, pprescription, paddress, pphone);
      long result = dbHelper.addPrescription(pname,pdiagnosis,paddress,pphone,pprescription);

      if (result<0 && result2<0){
          Toast.makeText(getApplicationContext(),"Failed to Add",Toast.LENGTH_LONG).show();
      }else{
          Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_LONG).show();
      }

    }

    private boolean nameIsfull(){
        String vname = name.getText().toString().trim();

        if (vname.isEmpty()){
            Toast.makeText(getApplicationContext(),"Name Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean addressIsfull(){
        String vaddress = addrs.getText().toString().trim();

        if (vaddress.isEmpty()){
            Toast.makeText(getApplicationContext(),"Address Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean mobileIsfull(){
        String vmobile = phone.getText().toString().trim();

        if (vmobile.isEmpty()){
            Toast.makeText(getApplicationContext(),"Phone Number Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean diagnosisIsfull(){
        String vdiag = diag.getText().toString().trim();

        if (vdiag.isEmpty()){
            Toast.makeText(getApplicationContext(),"Diagnosis Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean prescriptionIsfull(){
        String vpres = pres.getText().toString().trim();

        if (vpres.isEmpty()){
            Toast.makeText(getApplicationContext(),"Prescription Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }


    private boolean ValidatePhoneNo(){
        String vmobile = phone.getText().toString().trim();
        if(vmobile.length() == 10){
            return true;
        }

        else {
            Toast.makeText(getApplicationContext(),"Invalid Phone number",Toast.LENGTH_LONG).show();
            return false;
        }
    }



}
