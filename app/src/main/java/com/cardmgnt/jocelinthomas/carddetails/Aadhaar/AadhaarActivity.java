package com.cardmgnt.jocelinthomas.carddetails.Aadhaar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.EnterPassword;
import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;
import com.cardmgnt.jocelinthomas.carddetails.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class AadhaarActivity extends AppCompatActivity implements View.OnClickListener {

    TextView t1;
    Button buttonok, buttonScan;
    EditText edit1, edit2, edit3, aadhaarname;
    // variables to store extracted xml data
    String uid, name;

    //Database Helper Class
    DBHelper dbHelper;

    boolean shouldExecuteOnResume;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhaar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

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
        t1 = (TextView) findViewById(R.id.aadhaartitle);
        buttonok = (Button) findViewById(R.id.buttonok);
        buttonScan = (Button) findViewById(R.id.buttonScan);
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit3 = (EditText) findViewById(R.id.edit3);
        aadhaarname = (EditText) findViewById(R.id.aadhaarname);


        buttonScan.setOnClickListener(this);
        buttonok.setOnClickListener(this);

        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
        aadhaarname.setText("");

        aadhaarname.setNextFocusDownId(R.id.edit1); //verify it .


        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edit1.getText().toString().trim().length() == 4) {
                    edit2.requestFocus();

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edit2.getText().toString().trim().length() == 4) {
                    edit3.requestFocus();

                }

                if (edit2.getText().toString().trim().length() == 0) {
                    edit1.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edit3.getText().toString().trim().length() == 4) {

                }
                if (edit3.getText().toString().trim().length() == 0) {
                    edit2.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


 /*   @Override
    protected void onResume() {
        super.onResume();
        if (shouldExecuteOnResume) {

            SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
            String password = sharedPreferences.getString("password", "");

            startActivity(new Intent(AadhaarActivity.this, EnterPassword.class));
        } else {
            shouldExecuteOnResume = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //We will get scan results here
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (scanningResult != null) {

            //we have a result
            String scanContent = scanningResult.getContents();

            // process received data
            if (scanContent != null && !scanContent.isEmpty()) {
                processScannedData(scanContent);
            } else {
//                Toast toast = Toast.makeText(getApplicationContext(), "Scan Cancelled", Toast.LENGTH_SHORT);
//                toast.show();

                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Scan Cancelled.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);

                snackbar.show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
           // Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT).show();

            Snackbar snackbar = Snackbar.make(coordinatorLayout, "No data received.", Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);

            snackbar.show();
            //  super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void processScannedData(String scanData) {

        XmlPullParserFactory pullParserFactory;

        try {
            // init the parserfactory
            pullParserFactory = XmlPullParserFactory.newInstance();
            // get the parser
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(scanData));

            // parse the XML
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    Log.d("Rajdeol", "Start document");
                } else if (eventType == XmlPullParser.START_TAG && DataAttributes.AADHAAR_DATA_TAG.equals(parser.getName())) {
                    // extract data from tag
                    //uid
                    uid = parser.getAttributeValue(null, DataAttributes.AADHAR_UID_ATTR);
                    //name
                    name = parser.getAttributeValue(null, DataAttributes.AADHAR_NAME_ATTR);
                   /* //gender
                    gender = parser.getAttributeValue(null,DataAttributes.AADHAR_GENDER_ATTR);
                    // year of birth
                    yearOfBirth = parser.getAttributeValue(null,DataAttributes.AADHAR_YOB_ATTR);
                    // care of
                    careOf = parser.getAttributeValue(null,DataAttributes.AADHAR_CO_ATTR);
                    // village Tehsil
                    villageTehsil = parser.getAttributeValue(null,DataAttributes.AADHAR_VTC_ATTR);
                    // Post Office
                    postOffice = parser.getAttributeValue(null,DataAttributes.AADHAR_PO_ATTR);
                    // district
                    district = parser.getAttributeValue(null,DataAttributes.AADHAR_DIST_ATTR);
                    // state
                    state = parser.getAttributeValue(null,DataAttributes.AADHAR_STATE_ATTR);
                    // Post Code
                    postCode = parser.getAttributeValue(null,DataAttributes.AADHAR_PC_ATTR);*/

                } else if (eventType == XmlPullParser.END_TAG) {
                    Log.d("Rajdeol", "End tag " + parser.getName());

                } else if (eventType == XmlPullParser.TEXT) {
                    Log.d("Rajdeol", "Text " + parser.getText());

                }
                // update eventType
                eventType = parser.next();
            }

            // display the data on screen
            displayScannedData();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void displayScannedData() {

        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
        aadhaarname.setText("");
        String Adhaarnum = uid;
        String Adhaarname = name;

        String num1 = Adhaarnum.substring(0, 4);
        String num2 = Adhaarnum.substring(4, 8);
        String num3 = Adhaarnum.substring(8, 12);

        edit1.setText(num1);
        edit2.setText(num2);
        edit3.setText(num3);
        aadhaarname.setText(Adhaarname);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonok) {

            if (edit1.getText().toString().trim().isEmpty() || edit2.getText().toString().trim().isEmpty() || edit3.getText().toString().trim().isEmpty() || aadhaarname.getText().toString().trim().isEmpty())

            {
                // Toast.makeText(AadhaarActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar.make(view, "Please enter name and number.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);

                snackbar.show();

            } else if (edit1.getText().toString().trim().length() < 4 || edit2.getText().toString().trim().length() < 4 || edit3.getText().toString().trim().length() < 4)

            {
               // Toast.makeText(AadhaarActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar.make(view, "Please enter all the fields.", Snackbar.LENGTH_LONG);

                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);

                snackbar.show();
            } else {


                String num1 = edit1.getText().toString().trim();
                String num2 = edit2.getText().toString().trim();
                String num3 = edit3.getText().toString().trim();

                String Aadhaarname = aadhaarname.getText().toString().trim();
                String Aadhaarnum = num1 + num2 + num3;

                // FirebaseUser user = mAuth.getCurrentUser();
                //user.getUid();

                boolean result = Verhoeff.validateVerhoeff(Aadhaarnum);
                String msg = String.valueOf(result);
                if (msg == "true") {
                    //save values to db
                    dbHelper.insertAadhaarData(Aadhaarname, Aadhaarnum);
                    Intent intent = new Intent(AadhaarActivity.this, AadhaarCardDetails.class);
                    startActivity(intent);
                } else {
                   // Toast.makeText(AadhaarActivity.this, "Invalid details entered!!", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(view, "Invalid details are entered.", Snackbar.LENGTH_LONG);

                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);

                    snackbar.show();
                }

            }
        }
        if (view == buttonScan) {
            new IntentIntegrator(AadhaarActivity.this).setCaptureActivity(ScannerActivity.class).initiateScan();

        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(AadhaarActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
