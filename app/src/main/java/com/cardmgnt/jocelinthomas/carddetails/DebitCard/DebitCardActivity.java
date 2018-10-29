package com.cardmgnt.jocelinthomas.carddetails.DebitCard;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivingLicenseActivity;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivingLicense_Details;
import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;
import com.cardmgnt.jocelinthomas.carddetails.R;

public class DebitCardActivity extends AppCompatActivity {

    EditText name, number;
    Button submit;
    DBHelper dbHelper;

    boolean shouldExecuteOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_card);
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

        name = (EditText) findViewById(R.id.debitcardname);
        number = (EditText) findViewById(R.id.debitcardnum);
        submit = (Button) findViewById(R.id.btndebitcardsubmit);

        name.setText("");
        number.setText("");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String debitcardnumber = number.getText().toString().trim();
                String debitcardname = name.getText().toString().trim();

                if (debitcardname.isEmpty() || debitcardnumber.isEmpty()) {
                    Toast.makeText(DebitCardActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   /* Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

                    Matcher matcher = pattern.matcher(debitcardnumber);

                    if (matcher.matches()) {*/
                    dbHelper.insertDebitCardData(debitcardname,debitcardnumber);
                    Intent intent = new Intent(DebitCardActivity.this,DebitCardDetailsActivity.class);
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
    public void onBackPressed()
    {

        Intent intent = new Intent(DebitCardActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}
