package com.example.pharmeasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pharmeasy.Adapter.DeliveryAdapter;
import com.example.pharmeasy.Model.Delivery;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    private List<Delivery> deliveryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DeliveryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new DeliveryAdapter(deliveryList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

//        Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(DeliveryActivity.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mySpinner.setAdapter(myAdapter);

        initOrdersData();
    }

    private void initOrdersData() {
        Delivery delivery;

        delivery = new Delivery("Ed Burnette", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        deliveryList.add(delivery);

        delivery = new Delivery("Mark Murphy", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        deliveryList.add(delivery);

        delivery = new Delivery( " W. Frank Ableson", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        deliveryList.add(delivery);

        delivery = new Delivery( "Wei Meng Lee", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        deliveryList.add(delivery);

        delivery = new Delivery( "Sheran Gunasekera", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        deliveryList.add(delivery);

        mAdapter.notifyDataSetChanged();
    }
}
