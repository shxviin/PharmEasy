package com.example.pharmeasy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pharmeasy.Model.Medicine;
import com.example.pharmeasy.R;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
    private List<Medicine> medicineList;

    public MedicineAdapter(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }



    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_list_row, parent, false);

        return new MedicineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int position) {
        holder.medicine.setText(medicineList.get(position).getMedicine());
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public class MedicineViewHolder extends RecyclerView.ViewHolder {
        public TextView medicine;

        public MedicineViewHolder(View view) {
            super(view);
            medicine = view.findViewById(R.id.medicine);
        }
    }
}
