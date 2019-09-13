package com.example.pharmeasy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pharmeasy.Model.Medicine;
import com.example.pharmeasy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMedicineActivity extends AppCompatActivity {

    EditText txt_medicine;
    ImageButton btn_save, btn_clear;
    Medicine med;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.3));

        txt_medicine = findViewById(R.id.txtMedicine);

        btn_save = findViewById(R.id.btnSave);
        btn_clear = findViewById(R.id.btnClear);

        med = new Medicine();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("medicine");

                if (TextUtils.isEmpty(txt_medicine.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_LONG).show();
                }
                else{

                    med.setMedicine(txt_medicine.getText().toString().trim());

                    dbRef.push().setValue(med);
//                dbRef.child("IT001").setValue(med);
                    Toast.makeText(getApplicationContext(), "Medicine added successfully", Toast.LENGTH_LONG).show();
                    clearData();
                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cleared data", Toast.LENGTH_LONG).show();
                clearData();
            }
        });
    }

    public void clearData(){
        txt_medicine.setText("");
    }

}
