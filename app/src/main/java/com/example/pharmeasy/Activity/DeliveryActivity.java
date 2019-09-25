package com.example.pharmeasy.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pharmeasy.Adapter.DeliveryAdapter;
import com.example.pharmeasy.Model.Delivery;
import com.example.pharmeasy.R;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "PharmEasyDB";

    SQLiteDatabase mDatabase;
    ListView listViewDelivery;
    DeliveryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        listViewDelivery = findViewById(R.id.listViewDelivery);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        showDeliveryFromDatabase();
    }

    private void showDeliveryFromDatabase() {
        List<Delivery> deliveryList = new ArrayList<>();
        //we used rawQuery(sql, selectionargs) for fetching all the orders
        Cursor cursorDelivery = mDatabase.rawQuery("SELECT * FROM delivery", null);

        //if the cursor has some data
        if (cursorDelivery.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the order list
                deliveryList.add(new Delivery(
                        cursorDelivery.getInt(0),
                        cursorDelivery.getString(1),
                        cursorDelivery.getString(2),
                        cursorDelivery.getString(3),
                        cursorDelivery.getString(4),
                        cursorDelivery.getString(5)
                ));
            } while (cursorDelivery.moveToNext());
        }
        //closing the cursor
        cursorDelivery.close();

        //creating the adapter object
        adapter = new DeliveryAdapter(this, R.layout.delivery_list_row, deliveryList, mDatabase);

        //adding the adapter to listview
        listViewDelivery.setAdapter(adapter);
//        reloadOrdersFromDatabase();
    }
}
