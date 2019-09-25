package com.example.pharmeasy.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmeasy.Model.Delivery;
import com.example.pharmeasy.R;

import java.util.List;

public class DeliveryAdapter extends ArrayAdapter<Delivery> {

    Context mCtx;
    int listLayoutRes;
    List<Delivery> deliveryList;
    SQLiteDatabase mDatabase;
    Button btnEditDelivery, btnRemove;

    public DeliveryAdapter(Context mCtx, int listLayoutRes, List<Delivery> deliveryList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, deliveryList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.deliveryList = deliveryList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting employee of the specified position
        final Delivery delivery = deliveryList.get(position);

        //getting views
        TextView textViewCustomerName = view.findViewById(R.id.txtCustomerName);
        TextView textViewPrescription = view.findViewById(R.id.txtPrescription);
        TextView textAddress = view.findViewById(R.id.txtAddress);
        TextView textViewPhone = view.findViewById(R.id.txtPhone);
        TextView textViewStatus = view.findViewById(R.id.txtStatus);

        //adding data to views
        textViewCustomerName.setText(delivery.getCustomer());
        textViewPrescription.setText(delivery.getPrescription());
        textAddress.setText(delivery.getAddress());
        textViewPhone.setText(delivery.getPhone());
        textViewStatus.setText(delivery.getStatus());

        //we will use these buttons later for move operation
        btnEditDelivery = view.findViewById(R.id.btnEditDelivery);

        btnEditDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent edit_delivery_intent = new Intent(getContext(), EditDeliveryActivity.class);
//                mCtx.startActivity(edit_delivery_intent);
                updateDelivery(delivery);
            }
        });

        btnRemove = view.findViewById(R.id.btnRemove);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "The order has been moved to Delivery", Toast.LENGTH_SHORT).show();

                String sql2 = "INSERT INTO orders(cusName, prescription, address, phone) SELECT cusName, prescription, address, phone FROM delivery WHERE id = ?";

                mDatabase.execSQL(sql2, new Integer[]{delivery.getId()});

                String sql = "DELETE FROM delivery WHERE id = ?";
                mDatabase.execSQL(sql, new Integer[]{delivery.getId()});


            }
        });

        return view;
    }

    public void updateDelivery(final Delivery delivery){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_edit_delivery, null);
        builder.setView(view);

        final  TextView txtName = view.findViewById(R.id.txtName);
        final  TextView txtPrescription = view.findViewById(R.id.txtPrescription);
        final Spinner spinnerStatus = view.findViewById(R.id.spinnerStatus);

        txtName.setText(delivery.getCustomer());
        txtPrescription.setText(delivery.getPrescription());

        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}
