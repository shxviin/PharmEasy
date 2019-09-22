package com.example.pharmeasy.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pharmeasy.Adapter.OrdersAdapter;
import com.example.pharmeasy.Model.Orders;
import com.example.pharmeasy.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "myOrdersDatabase";

    private List<Orders> ordersList;
    SQLiteDatabase mDatabase;
    ListView listViewOrders;
    OrdersAdapter adapter;
    Button btnDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        listViewOrders = findViewById(R.id.listViewOrders);
        ordersList = new ArrayList<>();

        btnDemo = findViewById(R.id.btnDemo);

        //opening the database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createEmployeeTable();

        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });

        //this method will display the orders in the list
        showOrdersFromDatabase();
    }

    private void createEmployeeTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS orders (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT orders_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    cusname varchar(200) NOT NULL,\n" +
                        "    prescription varchar(200) NOT NULL,\n" +
                        "    address varchar(200) NOT NULL,\n" +
                        "    phone varchar(20) NOT NULL\n" +
                        ");"
        );
    }

    //In this method we will do the create operation
    private void addEmployee() {

            String insertSQL = "INSERT INTO orders \n" +
                    "(cusname, prescription, address, phone)\n" +
                    "VALUES \n" +
                    "('Shavin Kanagar', 'Panadol X 5', '36/2, Madampititya Rd', '0774592569');";
            mDatabase.execSQL(insertSQL);
            Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();
        reloadOrdersFromDatabase();
    }

    private void showOrdersFromDatabase() {
        //we used rawQuery(sql, selectionargs) for fetching all the orders
        Cursor cursorOrders = mDatabase.rawQuery("SELECT * FROM orders", null);

        //if the cursor has some data
        if (cursorOrders.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the order list
                ordersList.add(new Orders(
                        cursorOrders.getInt(0),
                        cursorOrders.getString(1),
                        cursorOrders.getString(2),
                        cursorOrders.getString(3),
                        cursorOrders.getString(4)
                ));
            } while (cursorOrders.moveToNext());
        }
        //closing the cursor
        cursorOrders.close();

        //creating the adapter object
        adapter = new OrdersAdapter(this, R.layout.orders_list_row, ordersList, mDatabase);

        //adding the adapter to listview
        listViewOrders.setAdapter(adapter);
        reloadOrdersFromDatabase();
    }

    public void reloadOrdersFromDatabase() {
        Cursor cursorOrders = mDatabase.rawQuery("SELECT * FROM orders", null);
        if (cursorOrders.moveToFirst()) {
            ordersList.clear();
            do {
                ordersList.add(new Orders(
                        cursorOrders.getInt(0),
                        cursorOrders.getString(1),
                        cursorOrders.getString(2),
                        cursorOrders.getString(3),
                        cursorOrders.getString(4)
                ));
            } while (cursorOrders.moveToNext());
        }
        cursorOrders.close();
        adapter.notifyDataSetChanged();
    }
}
