package com.example.pharmeasy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

public class editinfoActivity extends AppCompatActivity {
    EditText textInputUsername;
    EditText textInputMobile;
    EditText textInputAddress;
    Button mButtonUpdate;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);
        dbHelper = new DBHelper(this);

        textInputUsername = (EditText)findViewById(R.id.txtpatient);
        textInputUsername.setText(dbHelper.getUsername());
        textInputMobile = (EditText)findViewById(R.id.mobile);
        textInputMobile.setText(dbHelper.getMobile());
        textInputAddress = (EditText)findViewById(R.id.txtAddress);
        textInputAddress.setText(dbHelper.getAddress());
        mButtonUpdate = (Button)findViewById(R.id.button_update);
        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUsername();
                validateMobile();
                validateaddress();

                if (validateUsername() == true &&  validateMobile() == true &&  validateaddress()== true ){
                    editinfo();
                    Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    private void editinfo(){

        String uname = textInputUsername.getText().toString().trim();
        String mob = textInputMobile.getText().toString().trim();
        String address = textInputAddress.getText().toString().trim();


        dbHelper.changeinfo(uname,mob,address);
    }

    private boolean validateUsername() {
        String usernameInput = textInputUsername.getText().toString().trim();
        String input;
        if (usernameInput.isEmpty()) {
            input =  "Username cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }

    }



    private boolean validateMobile() {
        String passwordInput = textInputMobile.getEditableText().toString().trim();
        String input;
        int len = passwordInput.length();
        if (passwordInput.isEmpty()) {
            input =  "Password cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordInput.length() > 10){
            input =  "Enter a valid mobile number";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }

    }

    private boolean validateaddress() {
        String addressInput = textInputAddress.getText().toString().trim();
        String input;
        if (addressInput.isEmpty()) {
            input =  "Address cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }


    }
}
