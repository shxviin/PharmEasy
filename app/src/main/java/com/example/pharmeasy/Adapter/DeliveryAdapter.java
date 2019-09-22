package com.example.pharmeasy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmeasy.Model.Delivery;
import com.example.pharmeasy.R;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {
    private List<Delivery> deliveryList;

    public DeliveryAdapter(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }



    @Override
    public DeliveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delivery_list_row, parent, false);

        return new DeliveryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeliveryViewHolder holder, int position) {
        holder.customer.setText(deliveryList.get(position).getCustomer());
        holder.prescription.setText(deliveryList.get(position).getPrescription());
        holder.address.setText(deliveryList.get(position).getAddress());
        holder.phone.setText(deliveryList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return deliveryList.size();
    }

    public class DeliveryViewHolder extends RecyclerView.ViewHolder {
        public TextView customer;
        public TextView prescription;
        public TextView address;
        public TextView phone;

        public DeliveryViewHolder(View view) {
            super(view);
            customer = view.findViewById(R.id.txtCustomerName);
            prescription = view.findViewById(R.id.txtPrescription);
            address = view.findViewById(R.id.txtAddress);
            phone = view.findViewById(R.id.txtPhone);
        }
    }
}
