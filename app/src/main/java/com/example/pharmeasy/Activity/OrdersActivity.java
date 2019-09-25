package com.example.pharmeasy.Activity;

import android.app.SearchManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pharmeasy.Adapter.OrdersAdapter;
import com.example.pharmeasy.Model.Orders;
import com.example.pharmeasy.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class OrdersActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "PharmEasyDB";

//    List<Orders> ordersList;
    SQLiteDatabase mDatabase;
    ListView listViewOrders;
    OrdersAdapter adapter;
    Button btnDemo, btnShowOrders;
    SearchView txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        txtSearch = findViewById(R.id.txt_search);
        listViewOrders = findViewById(R.id.listViewOrders);
//        ordersList = new ArrayList<>();

        btnDemo = findViewById(R.id.btnDemo);
        btnShowOrders = findViewById(R.id.btnShowOrders);

        //opening the database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

//        createEmployeeTable();

        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        });

        btnShowOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrdersFromDatabase();
            }
        });
        //this method will display the orders in the list
            showOrdersFromDatabase();
//        test(FALSE);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        txtSearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        txtSearch.setSubmitButtonEnabled(true);
        txtSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),"Failed to Add",Toast.LENGTH_LONG).show();
                searchOrders(query);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
//                searchOrders(newText);
                return false;
            }
        });

    }

    /*
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
    */

    //In this method we will do the create operation
    private void addOrder() {

            String insertSQL = "INSERT INTO orders \n" +
                    "(cusname, prescription, address, phone)\n" +
                    "VALUES \n" +
                    "('Shavin Kanagar', 'Panadol X 5', '36/2, Madampititya Rd', '0774592569');";
            mDatabase.execSQL(insertSQL);
            Toast.makeText(this, "Order Added Successfully", Toast.LENGTH_SHORT).show();
        reloadOrdersFromDatabase();
    }

    private void showOrdersFromDatabase() {
        List<Orders> ordersList = new ArrayList<>();
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
//        reloadOrdersFromDatabase();
    }

    public void reloadOrdersFromDatabase() {
        List<Orders> ordersList = new ArrayList<>();
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

    private void searchOrders(String search){

        List<Orders> ordersList = new ArrayList<>();
        Cursor cursorOrders = mDatabase.rawQuery("SELECT * FROM orders WHERE prescription LIKE '" + search + "%'", null);
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
    }


}
