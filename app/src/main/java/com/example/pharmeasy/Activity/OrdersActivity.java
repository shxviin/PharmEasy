package com.example.pharmeasy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pharmeasy.Adapter.OrdersAdapter;
import com.example.pharmeasy.Model.Orders;
import com.example.pharmeasy.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private List<Orders> ordersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrdersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new OrdersAdapter(ordersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initOrdersData();
    }

    private void initOrdersData() {
        Orders order;

        order = new Orders("Ed Burnette", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        ordersList.add(order);

        order = new Orders("Mark Murphy", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        ordersList.add(order);

        order = new Orders( " W. Frank Ableson", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        ordersList.add(order);

        order = new Orders( "Wei Meng Lee", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        ordersList.add(order);

        order = new Orders( "Sheran Gunasekera", "5 x Panadol", "36/2A, Madampitiya Rd, Col - 15", "0774592569");
        ordersList.add(order);

        mAdapter.notifyDataSetChanged();
    }
}
