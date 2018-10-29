package com.cardmgnt.jocelinthomas.carddetails.Aadhaar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
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
import android.widget.TextView;

import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;
import com.cardmgnt.jocelinthomas.carddetails.R;
import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;


public class AadhaarCardDetails extends AppCompatActivity {

    DBHelper database;
    RecyclerView recyclerView;
    AadhaarCardAdapter recycler;
    List<DataModel> datamodel;
    private CoordinatorLayout coordinatorLayout;

    FloatingActionButton fab;
    //ShowcaseView.Builder showcaseview;
    private static final String SHOWCASE_ID = "Simple Showcase";
    String aadhar_name,aadhar_number;
    boolean shouldExecuteOnResume;

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    View rootLayout;

    private int revealX;
    private int revealY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhaar_card_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        final Intent intent = getIntent();

        rootLayout = findViewById(R.id.coordinatorLayout);

        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y))
        {
        }
        else {
            rootLayout.setVisibility(View.VISIBLE);
        }





        fab = (FloatingActionButton) findViewById(R.id.fab);
        datamodel = new ArrayList<DataModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        database = new DBHelper(AadhaarCardDetails.this);
        datamodel = database.getdata();
        recycler = new AadhaarCardAdapter(this, datamodel, fab);

        Log.i("HIteshdata", "" + datamodel);
        System.out.println("sssss"+datamodel);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recycler);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AadhaarCardDetails.this, AadhaarActivity.class);
                startActivity(intent);



            }
        });
        showMaterialCase(100);


    }

  /*  @Override
    protected void onResume() {
        super.onResume();
        if (shouldExecuteOnResume) {

            SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
            String password = sharedPreferences.getString("password", "");

            startActivity(new Intent(AadhaarCardDetails.this, EnterPassword.class));
        } else {
            shouldExecuteOnResume = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }*/

    private void showMaterialCase(int i) {

        new MaterialShowcaseView.Builder(AadhaarCardDetails.this) // instantiate the material showcase view using Builder
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
        Intent intent = new Intent(AadhaarCardDetails.this, HomeActivity.class);
        startActivity(intent);
    }

}
