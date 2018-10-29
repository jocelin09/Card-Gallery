package com.cardmgnt.jocelinthomas.carddetails.DrivingLicense;

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
import com.cardmgnt.jocelinthomas.carddetails.Pan.PanCardActivity;
import com.cardmgnt.jocelinthomas.carddetails.Pan.PanCardDetails;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrivingLicenseActivity extends AppCompatActivity {


    EditText name, number;
    Button submit;
    DBHelper dbHelper;

    boolean shouldExecuteOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_license);

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

        name = (EditText) findViewById(R.id.drivername);
        number = (EditText) findViewById(R.id.drivernum);
        submit = (Button) findViewById(R.id.btndriversubmit);

        name.setText("");
        number.setText("");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String drivernumber = number.getText().toString().trim();
                String drivername = name.getText().toString().trim();

                if (drivername.isEmpty() || drivernumber.isEmpty()) {
                    Toast.makeText(DrivingLicenseActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                   /* Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

                    Matcher matcher = pattern.matcher(drivernumber);

                    if (matcher.matches()) {*/
                        dbHelper.insertDriversdata(drivername,drivernumber);
                        Intent intent = new Intent(DrivingLicenseActivity.this,DrivingLicense_Details.class);
                        startActivity(intent);

                    /*} else {
                        Toast.makeText(DrivingLicenseActivity.this, "Invalid details entered!!", Toast.LENGTH_SHORT).show();

                    }
*/
                }


            }
        });
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(DrivingLicenseActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}
