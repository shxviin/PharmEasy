package com.example.pharmeasy;

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

public class RegisterActivity extends AppCompatActivity {


    TextView mTextViewLogin;
    EditText textInputUsername;
    EditText textInputPassword;
    EditText textInputConfirmPassword;
    EditText textInputMobile;
    Button mButtonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        textInputUsername = (EditText)findViewById(R.id.username);
        textInputPassword = (EditText)findViewById(R.id.pw);
        textInputConfirmPassword = (EditText)findViewById(R.id.confirm_pw);
        textInputMobile = (EditText)findViewById(R.id.mobile);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.user));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

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
                validateUsername();
                validatePassword();
                validateMobile();
                validateConfirmPassword();
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

    private void validatePassword() {
        String passwordInput = textInputPassword.getText().toString().trim();
        String input;
        int len = passwordInput.length();
        if (passwordInput.isEmpty()) {
            input =  "Password cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }
        else if (passwordInput.length() <=8){
            input =  "Password must be atleast 8 characters";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }


    }


    private void validateConfirmPassword() {
        String i = textInputConfirmPassword.getText().toString().trim();
        String passwordInput = textInputPassword.getText().toString().trim();
        String input;

        if (i.isEmpty()) {
            input =  "You did not confirm your password";
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








}
