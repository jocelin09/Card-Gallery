package com.cardmgnt.jocelinthomas.carddetails.Pan;

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

public class PanCardActivity extends AppCompatActivity {

    EditText name, number;
    Button submit;
    DBHelper dbHelper;

    boolean shouldExecuteOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_card);
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

        name = (EditText) findViewById(R.id.panname);
        number = (EditText) findViewById(R.id.pancardnum);
        submit = (Button) findViewById(R.id.btnpansubmit);

        name.setText("");
        number.setText("");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pannumber = number.getText().toString().trim();
                String panname = name.getText().toString().trim();

                if (panname.isEmpty() || pannumber.isEmpty()) {
                    Toast.makeText(PanCardActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

                    Matcher matcher = pattern.matcher(pannumber);

                    if (matcher.matches()) {
                        dbHelper.insertPanData(panname, pannumber);
                        Intent intent = new Intent(PanCardActivity.this, PanCardDetails.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(PanCardActivity.this, "Invalid details entered!!", Toast.LENGTH_SHORT).show();

                    }

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

            startActivity(new Intent(PanCardActivity.this, EnterPassword.class));
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
        Intent intent = new Intent(PanCardActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}
