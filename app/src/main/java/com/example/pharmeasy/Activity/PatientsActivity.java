package com.example.pharmeasy.Activity;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PatientsActivity extends AppCompatActivity {
   private static final String TAG = "PatientsActivity";
    private ListView plist;
    DBHelper dbHelper;
    private Button add;
//    private SearchView editsearch = findViewById(R.id.search_View);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        plist = (ListView)findViewById(R.id.listview);
        dbHelper=new DBHelper(this);
        add = findViewById(R.id.add_patient);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addpatient = new Intent(PatientsActivity.this,AddPatientActivity.class);
                startActivity(addpatient);
            }
        });

        populateListView();
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//
//        editsearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        editsearch.setSubmitButtonEnabled(true);
//        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });


    }





    private void populateListView(){
        Log.d(TAG,"populateListView: Displaying data in the ListView. ");
        Cursor data = dbHelper.getPatient();
        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()){
            listData.add(data.getString(1));

        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        plist.setAdapter(adapter);

        plist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Log.d(TAG,"onItemClick: You Clicked on " + name);

                Cursor data = dbHelper.getPatientID(name);
                int pID = -1;
                String address = "", phone = "", diagnosis = "", prescription = "";
                while (data.moveToNext()){
                    pID = data.getInt(0);
                    diagnosis = data.getString(2);
                    address  = data.getString(3);
                    phone = data.getString(4);
                    prescription = data.getString(5);

                }
                if(pID > -1){
                    Log.d(TAG,"onItemClick: The Patient ID  " + pID);
                    Intent editpatient = new Intent(PatientsActivity.this,EditPatientActivity.class);
                    editpatient.putExtra("id",pID);
                    editpatient.putExtra("name",name);
                    editpatient.putExtra("diagnosis",diagnosis);
                    editpatient.putExtra("address",address);
                    editpatient.putExtra("phone",phone);
                    editpatient.putExtra("prescription",prescription);
                    startActivity(editpatient);


                }else {
                    Toast.makeText(getApplicationContext(),"No record found",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
