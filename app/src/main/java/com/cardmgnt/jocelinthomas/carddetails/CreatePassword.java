package com.cardmgnt.jocelinthomas.carddetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;

public class CreatePassword extends AppCompatActivity {

    EditText password1, password2;
    Button submit;
    CheckBox showpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        password1 = (EditText) findViewById(R.id.newpwd);
        password2 = (EditText) findViewById(R.id.reenterpwd);
        submit = (Button) findViewById(R.id.submit);
        showpwd = (CheckBox) findViewById(R.id.showpwd);
        showpwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {
                    // show password
                    password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtpassword1 = password1.getText().toString().trim();
                String txtpassword2 = password2.getText().toString().trim();

                if (txtpassword1.equals("") || txtpassword2.equals("")) {
                    Toast.makeText(CreatePassword.this, "Enter the fields", Toast.LENGTH_LONG).show();
                }
                else if(txtpassword1.length() == 4 || txtpassword2.length() == 4)
                {

                    if (txtpassword1.equals(txtpassword2)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password", txtpassword1);
                        editor.apply();


                        Intent intent = new Intent(CreatePassword.this, HomeActivity.class);
                        startActivity(intent);
                        finish();


                    } else {
                        Toast.makeText(CreatePassword.this, "Passwords doesn't match", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(CreatePassword.this, "Password should be of 4 digits!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
