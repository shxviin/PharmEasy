package com.example.pharmeasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pharmeasy.Adapter.MedicineAdapter;
import com.example.pharmeasy.Model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {

    private List<Medicine> medicineList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MedicineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new MedicineAdapter(medicineList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initOrdersData();
    }

    private void initOrdersData() {
        Medicine medicine;

        medicine = new Medicine("Paracetamol");
        medicineList.add(medicine);

        medicine = new Medicine("Ervamatin");
        medicineList.add(medicine);

        medicine = new Medicine( "Biotin Oil 100ml");
        medicineList.add(medicine);

        medicine = new Medicine( "Moov");
        medicineList.add(medicine);

        medicine = new Medicine( "Sidhaleppa");
        medicineList.add(medicine);

        medicine = new Medicine("Paracetamol");
        medicineList.add(medicine);

        medicine = new Medicine("Ervamatin");
        medicineList.add(medicine);

        medicine = new Medicine( "Biotin Oil 100ml");
        medicineList.add(medicine);

        medicine = new Medicine( "Moov");
        medicineList.add(medicine);

        medicine = new Medicine( "Sidhaleppa");
        medicineList.add(medicine);

        medicine = new Medicine("Paracetamol");
        medicineList.add(medicine);

        medicine = new Medicine("Ervamatin");
        medicineList.add(medicine);

        medicine = new Medicine( "Biotin Oil 100ml");
        medicineList.add(medicine);

        medicine = new Medicine( "Moov");
        medicineList.add(medicine);

        medicine = new Medicine( "Sidhaleppa");
        medicineList.add(medicine);

        mAdapter.notifyDataSetChanged();
    }
}
