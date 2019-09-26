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

public class EditPatientActivity extends AppCompatActivity {
    private static final String TAG = "EditPatientActivity";
    private Button button_register,button_delete;
    private EditText editname,editmobile,editaddress,editdiag,editpres;
    DBHelper dbHelper;

    private String sname,saddress,sdiagnosis,sprescription, sphone;
    private int sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        editname = (EditText)findViewById(R.id.txt_patient);
        editmobile = (EditText)findViewById(R.id.mobile_No);
        editaddress = (EditText)findViewById(R.id.txt_address);
        editdiag = (EditText)findViewById(R.id.txt_diagnosis);
        editpres = (EditText)findViewById(R.id.txt_Prescription);
        button_delete = findViewById(R.id.btn_deletePatient);
        button_register =findViewById(R.id.btn_editPatient);
        dbHelper = new DBHelper(this);
        Intent received = getIntent();
        sid = received.getIntExtra("id",-1);
        sname = received.getStringExtra("name");
        sdiagnosis = received.getStringExtra("diagnosis");
        saddress = received.getStringExtra("address");
        sphone = received.getStringExtra("phone");
        sprescription = received.getStringExtra("prescription");
        editname.setText(sname);
        editmobile.setText(sphone);
        editaddress.setText(saddress);
        editdiag.setText(sdiagnosis);
        editpres.setText(sprescription);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameIsfull()==true && addressIsfull()== true && mobileIsfull() == true && ValidatePhoneNo()==true && diagnosisIsfull() == true && prescriptionIsfull() == true) {

                    updatePatient();
                    Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                    Intent add_intent = new Intent(EditPatientActivity.this, PatientsActivity.class);
                    startActivity(add_intent);
                }
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deletePatient(sid,sname);
                Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_LONG).show();
                Intent add_intent = new Intent( EditPatientActivity.this, PatientsActivity.class);
                startActivity(add_intent);
            }
        });
    }


    private void updatePatient()
    {
        String pname= editname.getText().toString().trim();
        String paddress= editaddress.getText().toString().trim();
        String pphone= editmobile.getText().toString().trim();
        String pdiagnosis= editdiag.getText().toString().trim();
        String pprescription= editpres.getText().toString().trim();
        dbHelper.updatePatientname(pname,sid,sname);
        dbHelper.updatePatientaddress(paddress,sid);
        dbHelper.updatePatientmobile(pphone,sid);
        dbHelper.updatePatientdiagnosis(pdiagnosis,sid);
        dbHelper.updatePatientprescription(pprescription,sid);
    }
    private boolean nameIsfull(){
        String vname = editname.getText().toString().trim();

        if (vname.isEmpty()){
            Toast.makeText(getApplicationContext(),"Name Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean addressIsfull(){
        String vaddress = editaddress.getText().toString().trim();

        if (vaddress.isEmpty()){
            Toast.makeText(getApplicationContext(),"Address Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean mobileIsfull(){
        String vmobile = editmobile.getText().toString().trim();

        if (vmobile.isEmpty()){
            Toast.makeText(getApplicationContext(),"Phone Number Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean diagnosisIsfull(){
        String vdiag = editdiag.getText().toString().trim();

        if (vdiag.isEmpty()){
            Toast.makeText(getApplicationContext(),"Diagnosis Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean prescriptionIsfull(){
        String vpres = editpres.getText().toString().trim();

        if (vpres.isEmpty()){
            Toast.makeText(getApplicationContext(),"Prescription Field Cannot be Empty",Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }

    }


    private boolean ValidatePhoneNo(){
        String vmobile = editmobile.getText().toString().trim();
        if(vmobile.length() == 10){
            return true;
        }

        else {
            Toast.makeText(getApplicationContext(),"Invalid Phone number",Toast.LENGTH_LONG).show();
            return false;
        }
    }



}
