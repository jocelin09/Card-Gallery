package com.cardmgnt.jocelinthomas.carddetails.Mediclaim;

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


public class MediclaimActivity extends AppCompatActivity {

    EditText name, number;
    Button submit;
    DBHelper dbHelper;

    boolean shouldExecuteOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediclaim);

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

        name = (EditText) findViewById(R.id.mediclaimname);
        number = (EditText) findViewById(R.id.mediclaimnum);
        submit = (Button) findViewById(R.id.btnmediclaimsubmit);

        name.setText("");
        number.setText("");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mednumber = number.getText().toString().trim();
                String medname = name.getText().toString().trim();

                if (medname.isEmpty() || mednumber.isEmpty()) {
                    Toast.makeText(MediclaimActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
//                    Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
//
//                    Matcher matcher = pattern.matcher(pannumber);
//
//                    if (matcher.matches()) {
                        dbHelper.insertMediclaimdata(medname,mednumber);
                        Intent intent = new Intent(MediclaimActivity.this,MediclaimDetailsActivity.class);
                        startActivity(intent);

//                    } else {
//                        Toast.makeText(MediclaimActivity.this, "Invalid details entered!!", Toast.LENGTH_SHORT).show();
//
//                    }

                }


            }
        });
    }

/*    @Override
    protected void onResume() {
        super.onResume();
        if (shouldExecuteOnResume) {

            SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
            String password = sharedPreferences.getString("password", "");

            startActivity(new Intent(MediclaimActivity.this, EnterPassword.class));
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
        Intent intent = new Intent(MediclaimActivity.this, HomeActivity.class);
        startActivity(intent);

    }

}
