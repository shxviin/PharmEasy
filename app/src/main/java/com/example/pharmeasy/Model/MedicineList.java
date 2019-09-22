package com.example.pharmeasy.Model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmeasy.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MedicineList extends ArrayAdapter<Medicine> {

    private Activity context;
    List<Medicine> medicines;
    DatabaseReference dbRef;
    public MedicineList(Activity context, List<Medicine> medicines, DatabaseReference dbRef) {
        super(context, R.layout.medicine_list_row, medicines);
        this.context = context;
        this.medicines = medicines;
        this.dbRef = dbRef;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.medicine_list_row, null, true);

        TextView txtName = listViewItem.findViewById(R.id.txtName);
        Button btnDelete = listViewItem.findViewById(R.id.btnDelete);

        final Medicine medicine = medicines.get(pos);
        txtName.setText(medicine.getMedicine());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef.child(medicine.getId()).removeValue();
                Toast.makeText(v.getContext(), "Medicine deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });


        return listViewItem;
    }
}
