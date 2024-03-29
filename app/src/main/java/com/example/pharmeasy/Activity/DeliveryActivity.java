package com.example.pharmeasy.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pharmeasy.Adapter.DeliveryAdapter;
import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.Model.Delivery;
import com.example.pharmeasy.Model.Orders;
import com.example.pharmeasy.R;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "PharmEasyDB";

    Button btnDemo;
    SQLiteDatabase mDatabase;
    ListView listViewDelivery;
    DeliveryAdapter adapter;
    DBHelper dbHelper;
    List<Delivery> deliveryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        dbHelper = new DBHelper(this);

        listViewDelivery = findViewById(R.id.listViewDelivery);

        btnDemo = findViewById(R.id.btnDemo);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDelivery();
            }
        });

        showDeliveryFromDatabase();
    }

    private void addDelivery() {

        String insertSQL = "INSERT INTO delivery \n" +
                "(cusname, prescription, address, phone, status, owner)\n" +
                "VALUES \n" +
                "('Shavin Kanagar', 'Panadol X 5', '36/2, Madampititya Rd', '0774592569', 'To Be Dispatched', 'Test');";
        mDatabase.execSQL(insertSQL);
        Toast.makeText(this, "Order Added Successfully", Toast.LENGTH_SHORT).show();
        reloadDeliveryFromDatabase();
    }

    private void reloadDeliveryFromDatabase() {
        Cursor cursorDelivery = mDatabase.rawQuery("SELECT * FROM delivery WHERE owner = '" + dbHelper.getUsername() + "'", null);
        if (cursorDelivery.moveToFirst()) {
            deliveryList.clear();
            do {
                deliveryList.add(new Delivery(
                        cursorDelivery.getInt(0),
                        cursorDelivery.getString(1),
                        cursorDelivery.getString(2),
                        cursorDelivery.getString(3),
                        cursorDelivery.getString(4),
                        cursorDelivery.getString(5),
                        cursorDelivery.getString(6)
                ));
            } while (cursorDelivery.moveToNext());
        }
        cursorDelivery.close();
        adapter.notifyDataSetChanged();
    }

    //This method retreives and displays all delivery from the database
    private void showDeliveryFromDatabase() {
        List<Delivery> deliveryList = new ArrayList<>();
        //we used rawQuery(sql, selectionargs) for fetching all the orders
        Cursor cursorDelivery = mDatabase.rawQuery("SELECT * FROM delivery WHERE owner = '" + dbHelper.getUsername() + "'", null);

        //if the cursor has some data
        if (cursorDelivery.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the delivery list
                deliveryList.add(new Delivery(
                        cursorDelivery.getInt(0),
                        cursorDelivery.getString(1),
                        cursorDelivery.getString(2),
                        cursorDelivery.getString(3),
                        cursorDelivery.getString(4),
                        cursorDelivery.getString(5),
                        cursorDelivery.getString(6)
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
