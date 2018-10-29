package com.cardmgnt.jocelinthomas.carddetails.CreditCard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivingLicenseActivity;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivingLicense_Details;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivinglicenseAdapter;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivinglicenseModel;
import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class CreditCardDetails extends AppCompatActivity {

    DBHelper database;
    RecyclerView recyclerView;
    CreditCardAdapter recycler;
    List<CreditCardModel> datamodel;
    private CoordinatorLayout coordinatorLayout;
    private static final String SHOWCASE_ID = "Simple Showcase";
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_details);
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
        datamodel = new ArrayList<CreditCardModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        database = new DBHelper(this);
        datamodel = database.getCreditCardData();
        recycler = new CreditCardAdapter(this, datamodel);

        Log.i("HIteshdata", "" + datamodel);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recycler);


         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreditCardDetails.this, CreditCardActivity.class);
                startActivity(intent);
            }
        });
        showMaterialCase(100);
    }

    private void showMaterialCase(int i) {
        new MaterialShowcaseView.Builder(CreditCardDetails.this) // instantiate the material showcase view using Builder
                 .setTarget(fab) // set what view will be pointed or highlighted
                //.setTitleText("Single") // set the title of the tutorial
                .setContentText("Click here to add the details") // set the content or detail text
                .setDismissText("GOT IT") // set the dismiss text
                .setDelay(i) // set delay in milliseconds to show the tutor
                .singleUse(SHOWCASE_ID)// set the single use so it is shown only once using our create SHOWCASE_ID constant
                .show();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreditCardDetails.this,HomeActivity.class);
        startActivity(intent);
    }


}
