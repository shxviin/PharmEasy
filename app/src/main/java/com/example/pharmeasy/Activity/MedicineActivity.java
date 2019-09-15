package com.example.pharmeasy.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.example.pharmeasy.Adapter.MedicineAdapter;
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

//    private List<Medicine> medicineList = new ArrayList<>();
//    private RecyclerView recyclerView;
//    private MedicineAdapter mAdapter;

    DatabaseReference dbRef;
    ListView listViewMedicines;
    List<Medicine> medicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

//        recyclerView = findViewById(R.id.recycler_view);
//
//        mAdapter = new MedicineAdapter(medicineList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);

        medicines = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("medicine");

        listViewMedicines = findViewById(R.id.listViewMedicines);

        FloatingActionButton button_medicine = findViewById(R.id.addMedFloatingButton);
        button_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_medicine_intent = new Intent( MedicineActivity.this, AddMedicineActivity.class);
                startActivity(add_medicine_intent);
            }
        });

//        initOrdersData();
    }

//    private void initOrdersData() {
//        Medicine medicine;
//
//
//
//        mAdapter.notifyDataSetChanged();
//    }


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

                MedicineList medicineAdapter = new MedicineList(MedicineActivity.this, medicines);
                listViewMedicines.setAdapter(medicineAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
