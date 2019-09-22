package com.example.pharmeasy.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmeasy.Activity.OrdersActivity;
import com.example.pharmeasy.Model.Orders;
import com.example.pharmeasy.R;

import java.util.List;

public class OrdersAdapter extends ArrayAdapter<Orders> {

    Context mCtx;
    int listLayoutRes;
    List<Orders> ordersList;
    SQLiteDatabase mDatabase;

    public OrdersAdapter(Context mCtx, int listLayoutRes, List<Orders> ordersList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, ordersList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.ordersList = ordersList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting employee of the specified position
        final Orders orders = ordersList.get(position);


        //getting views
        TextView textViewCustomerName = view.findViewById(R.id.txtCustomerName);
        TextView textViewPrescription = view.findViewById(R.id.txtPrescription);
        TextView textAddress = view.findViewById(R.id.txtAddress);
        TextView textViewPhone = view.findViewById(R.id.txtPhone);

        //adding data to views
        textViewCustomerName.setText(orders.getCustomerName());
        textViewPrescription.setText(orders.getPrescription());
        textAddress.setText(orders.getAddress());
        textViewPhone.setText(orders.getPhone());

        //we will use these buttons later for move operation
        Button buttonMoveOrder = view.findViewById(R.id.buttonMoveOrder);

        buttonMoveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "The order has been moved to Delivery", Toast.LENGTH_SHORT).show();
                String sql = "DELETE FROM orders WHERE id = ?";
                mDatabase.execSQL(sql, new Integer[]{orders.getId()});
            }
        });

        return view;
    }
}
