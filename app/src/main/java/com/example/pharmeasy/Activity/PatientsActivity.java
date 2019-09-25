package com.example.pharmeasy.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PatientsActivity extends AppCompatActivity {
   private static final String TAG = "PatientsActivity";
    private ListView plist;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        plist = (ListView)findViewById(R.id.listview);
        dbHelper=new DBHelper(this);

        populateListView();

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
    }
}
