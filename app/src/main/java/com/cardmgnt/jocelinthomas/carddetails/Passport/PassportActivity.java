package com.cardmgnt.jocelinthomas.carddetails.Passport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.EnterPassword;
import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportActivity extends AppCompatActivity {

    EditText passname, passportnumber;
    Button passsubmit;
    DBHelper dbHelper;

    boolean shouldExecuteOnResume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        dbHelper = new DBHelper(this);

        passname = (EditText) findViewById(R.id.passportname);
        passportnumber = (EditText) findViewById(R.id.passportnum);
        passsubmit = (Button) findViewById(R.id.btnpasssubmit);

        passname.setText("");
        passportnumber.setText("");

        passsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String passnumber = passportnumber.getText().toString().trim();
                String passportname = passname.getText().toString().trim();

                if (passportname.isEmpty() || passnumber.isEmpty()) {
                    Toast.makeText(PassportActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
//                    Pattern pattern = Pattern.compile("([A-PR-WY]){1}([1-9]){1}([0-9]){5}([1-9]){1}");
//
//                    Matcher matcher = pattern.matcher(passnumber);
//
//                    if (matcher.matches()) {
                        dbHelper.insertPassportdata(passportname, passnumber);
                        Intent intent = new Intent(PassportActivity.this, PassportDetailsActivity.class);
                        startActivity(intent);

//                    } else {
//                        Toast.makeText(PassportActivity.this, "Invalid details entered!!", Toast.LENGTH_SHORT).show();
//
//                    }

                }


            }
        });


    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        if (shouldExecuteOnResume) {

            SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
            String password = sharedPreferences.getString("password", "");

            startActivity(new Intent(PassportActivity.this, EnterPassword.class));
        } else {
            shouldExecuteOnResume = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }*/

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PassportActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}
