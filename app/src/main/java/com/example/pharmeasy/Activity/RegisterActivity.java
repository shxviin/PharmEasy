package com.example.pharmeasy.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

public class RegisterActivity extends AppCompatActivity {


    TextView mTextViewLogin;
    EditText textInputUsername;
    EditText textInputPassword;
    EditText textInputConfirmPassword;
    EditText textInputMobile;
    EditText textInputAddress;
    Button mButtonRegister;
    Spinner mySpinner;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         mySpinner = (Spinner) findViewById(R.id.spinner);
        textInputUsername = (EditText)findViewById(R.id.txtpatient);
        textInputPassword = (EditText)findViewById(R.id.pw);
        textInputConfirmPassword = (EditText)findViewById(R.id.confirm_pw);
        textInputMobile = (EditText)findViewById(R.id.mobile);
        textInputAddress = (EditText) findViewById(R.id.address);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.user));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        dbHelper = new DBHelper(this);

        mTextViewLogin = (TextView) findViewById(R.id.textview_login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateUsername() == true && validatePassword() == true && validateMobile() == true && validateConfirmPassword()== true ){
                    addUser();
                    Intent RegisterIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(RegisterIntent);
                    Toast.makeText(getApplicationContext(),"Successfully Registered!",Toast.LENGTH_LONG).show();
                }


            }
        });




    }


    private void addUser(){

        String uname = textInputUsername.getText().toString().trim();
        String pwd = textInputPassword.getText().toString().trim();
        String mob = textInputMobile.getText().toString().trim();
        String address = textInputAddress.getText().toString().trim();
        String type = mySpinner.getSelectedItem().toString().trim();


        dbHelper.addUser(uname,pwd,mob,address,type);
    }

    private Boolean validateUsername() {
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

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();
        String input;
        int len = passwordInput.length();
        if (passwordInput.isEmpty()) {
            input =  "Password cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordInput.length() <=8){
            input =  "Password must be atleast 8 characters";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;


        }
        else {
            return true;
        }


    }


    private boolean validateConfirmPassword() {
        String i = textInputConfirmPassword.getText().toString().trim();
        String passwordInput = textInputPassword.getText().toString().trim();
        String input;

        if (i.isEmpty()) {
            input =  "You did not confirm your password";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (i.equals(passwordInput) ){
            return true;
        }
        else {
            input =  "Password does not match";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }


    }



    private boolean validateMobile() {
        String passwordInput = textInputMobile.getEditableText().toString().trim();
        String input;
        int len = passwordInput.length();
        if (passwordInput.isEmpty()) {
            input =  "Mobile cannot be empty";
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








}
