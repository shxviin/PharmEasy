package com.example.pharmeasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatePassword extends AppCompatActivity {
    EditText textInputCurrentPassword;
    EditText textInputNewPassword;
    EditText textInputConfirmPassword;
    Button mButtonUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        textInputCurrentPassword = (EditText)findViewById(R.id.current_pw);
        textInputNewPassword = (EditText)findViewById(R.id.new_pw);
        textInputConfirmPassword = (EditText)findViewById(R.id.confirm_pw);

        mButtonUpdate = (Button)findViewById(R.id.button_update);
        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateNewPassword();
                validateConfirmPassword();
            }
        });
    }






    private void validateNewPassword() {
        String passwordInput = textInputNewPassword.getText().toString().trim();
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
        String passwordInput = textInputNewPassword.getText().toString().trim();
        String input;

        if (i.isEmpty()) {
            input =  "You did not confirm your password";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }



    }
}
