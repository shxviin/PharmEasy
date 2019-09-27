package com.example.pharmeasy.Adapter;

import android.app.AlertDialog;
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

        //getting delivery of the specified position
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

        btnEditDelivery = view.findViewById(R.id.btnEdit);

        btnEditDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDelivery(delivery);
            }
        });

        btnRemove = view.findViewById(R.id.btnRemove);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editDelivery(delivery);

                reloadEmployeesFromDatabase();
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

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = spinnerStatus.getSelectedItem().toString();

                String sql = "UPDATE delivery \n" +
                        "SET status = ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{ status, String.valueOf(delivery.getId())});
                Toast.makeText(mCtx, "Delivery Status Updated", Toast.LENGTH_SHORT).show();
                reloadEmployeesFromDatabase();

                dialog.dismiss();
            }
        });

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void editDelivery(final Delivery delivery){

        String status = delivery.getStatus();

        if (status.equals("Delivered")){
            Toast.makeText(mCtx, "Removed from Delivery", Toast.LENGTH_SHORT).show();

            String sql = "DELETE FROM delivery WHERE id = ?";
            mDatabase.execSQL(sql, new Integer[]{delivery.getId()});
        } else{
            Toast.makeText(mCtx, "Cannot remove until it is delivered", Toast.LENGTH_SHORT).show();
        }
        reloadEmployeesFromDatabase();
    }

    private void reloadEmployeesFromDatabase() {
        Cursor cursorDelivery = mDatabase.rawQuery("SELECT * FROM delivery", null);
        if (cursorDelivery.moveToFirst()) {
            deliveryList.clear();
            do {
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
        cursorDelivery.close();
        notifyDataSetChanged();
    }
}
