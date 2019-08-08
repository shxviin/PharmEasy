package com.example.pharmeasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editinfoActivity extends AppCompatActivity {
    EditText textInputUsername;
    EditText textInputMobile;
    EditText textInputAddress;
    Button mButtonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);

        textInputUsername = (EditText)findViewById(R.id.username);
        textInputMobile = (EditText)findViewById(R.id.mobile);
        textInputAddress = (EditText)findViewById(R.id.address);
        mButtonUpdate = (Button)findViewById(R.id.button_update);

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUsername();
                validateMobile();
                validateaddress();
            }
        });


    }



    private void validateUsername() {
        String usernameInput = textInputUsername.getText().toString().trim();
        String input;
        if (usernameInput.isEmpty()) {
            input =  "Username cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }


    }



    private void validateMobile() {
        String passwordInput = textInputMobile.getEditableText().toString().trim();
        String input;
        int len = passwordInput.length();
        if (passwordInput.isEmpty()) {
            input =  "Password cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }
        else if (passwordInput.length() > 10){
            input =  "Enter a valid mobile number";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }


    }

    private void validateaddress() {
        String addressInput = textInputAddress.getText().toString().trim();
        String input;
        if (addressInput.isEmpty()) {
            input =  "Address cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }


    }
}
