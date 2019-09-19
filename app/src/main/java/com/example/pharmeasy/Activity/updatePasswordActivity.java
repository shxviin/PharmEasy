package com.example.pharmeasy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

public class

updatePasswordActivity extends AppCompatActivity {
    EditText textInputCurrentPassword;
    EditText textInputNewPassword;
    EditText textInputConfirmPassword;
    Button mButtonUpdate;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        dbHelper = new DBHelper(this);
        textInputCurrentPassword = (EditText)findViewById(R.id.current_pw);
        textInputNewPassword = (EditText)findViewById(R.id.new_pw);
        textInputConfirmPassword = (EditText)findViewById(R.id.confirm_pw);

        mButtonUpdate = (Button)findViewById(R.id.button_update);
        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateNewPassword();
                validateConfirmPassword();

                if (validatepassword() == true){
                    if (validateNewPassword() == true && validateConfirmPassword() == true){
                        dbHelper.changepwd(textInputNewPassword.getText().toString().trim());
                        Toast.makeText(getApplicationContext(),"Password Updated",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


private boolean validatepassword(){

    String passwordInput = textInputCurrentPassword.getText().toString().trim();
    String input;

    if (passwordInput.isEmpty()) {
        input =  "Current Password cannot be empty";
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        return false;
    }
    else if (passwordInput.equals(dbHelper.getpwd())){
        return true;
    }
    else {
        return false;
    }




}



    private boolean validateNewPassword() {
        String passwordInput = textInputNewPassword.getText().toString().trim();
        String input;
        int len = passwordInput.length();
        if (passwordInput.isEmpty()) {
            input =  "New Password cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordInput.length() <=8){
            input =  "New Password must be atleast 8 characters";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }




    }


    private boolean validateConfirmPassword() {
        String i = textInputConfirmPassword.getText().toString().trim();
        String passwordInput = textInputNewPassword.getText().toString().trim();
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
            input =  "Confirm Password does not match";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        }


    }



    }

