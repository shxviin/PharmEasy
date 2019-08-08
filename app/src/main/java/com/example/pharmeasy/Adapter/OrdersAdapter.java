package com.example.pharmeasy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmeasy.Model.Orders;
import com.example.pharmeasy.R;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {
    private List<Orders> ordersList;

    public OrdersAdapter(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }



    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_list_row, parent, false);

        return new OrdersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrdersViewHolder holder, int position) {
        holder.customer.setText(ordersList.get(position).getCustomer());
        holder.prescription.setText(ordersList.get(position).getPrescription());
        holder.address.setText(ordersList.get(position).getAddress());
        holder.phone.setText(ordersList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {
        public TextView customer;
        public TextView prescription;
        public TextView address;
        public TextView phone;

        public OrdersViewHolder(View view) {
            super(view);
            customer = view.findViewById(R.id.customer);
            prescription = view.findViewById(R.id.prescription);
            address = view.findViewById(R.id.address);
            phone = view.findViewById(R.id.phone);
        }
    }
}
