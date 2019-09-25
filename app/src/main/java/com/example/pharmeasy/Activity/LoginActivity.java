package com.example.pharmeasy.Activity;




import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

public class LoginActivity extends AppCompatActivity {

    EditText textInputUsername;
    EditText textInputPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DBHelper dbHelper;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        textInputUsername = (EditText)findViewById(R.id.edittext_username);
        textInputPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validateUsername()== true &&  validatePassword() == true  ){
                    String type = checkUser();
                    if(type.equals("Pharmacy")){
                        Intent loginIntent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(loginIntent);
                    }
                    if(type.equals("Hospital")){
                        Intent loginIntent = new Intent(LoginActivity.this,HospitalActivity.class);
                        startActivity(loginIntent);
                    }
                    if(type.equals("Patient")){
                        Intent loginIntent = new Intent(LoginActivity.this,PatientsActivity.class);
                        startActivity(loginIntent);
                    }
                    Toast.makeText(getApplicationContext(),type,Toast.LENGTH_LONG).show();
                }
            }
        });
    }




    private boolean validateUsername() {
        String usernameInput = textInputUsername.getText().toString().trim();
        String input;
        if (usernameInput.isEmpty()) {
           input =  "Username cannot be empty";
           Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
           return  false;
        }
        else {
            return true;
        }

    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditableText().toString().trim();
        String input;

        if (passwordInput.isEmpty()) {
            input =  "Password cannot be empty";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return  false;
        }
        else {
            return true;
        }

    }

    private String checkUser(){
        String uname = textInputUsername.getText().toString().trim();
        String pwd = textInputPassword.getText().toString().trim();

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username",uname);
        editor.putString("password",pwd);
        editor.commit();
        String type = dbHelper.checkUser(uname,pwd);
        return type;


//       if(type == "Hospital"){
//           Intent loginIntent = new Intent(LoginActivity.this,MedicineActivity.class);
//           startActivity(loginIntent);
//       }

    }


}
