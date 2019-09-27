package com.example.pharmeasy.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.Model.Medicine;
import com.example.pharmeasy.Model.MedicineList;

import com.example.pharmeasy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    ListView listViewMedicines;
    List<Medicine> medicines;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        dbHelper = new DBHelper(this);

        medicines = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference(dbHelper.getUsername());

        listViewMedicines = findViewById(R.id.listViewMedicines);

        FloatingActionButton button_medicine = findViewById(R.id.addMedFloatingButton);
        button_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_medicine_intent = new Intent( MedicineActivity.this, AddMedicineActivity.class);
                startActivity(add_medicine_intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                medicines.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren() ){
                    Medicine medicine = postSnapshot.getValue(Medicine.class);
                    medicines.add(medicine);
                }

                MedicineList medicineAdapter = new MedicineList(MedicineActivity.this, medicines, dbRef);
                listViewMedicines.setAdapter(medicineAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
