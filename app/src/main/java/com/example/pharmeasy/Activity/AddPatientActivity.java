package com.example.pharmeasy.Activity;

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
                addPrescription();
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
}
