package com.cardmgnt.jocelinthomas.carddetails.VoterID;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.cardmgnt.jocelinthomas.carddetails.Aadhaar.AadhaarActivity;
import com.cardmgnt.jocelinthomas.carddetails.Aadhaar.AadhaarCardAdapter;
import com.cardmgnt.jocelinthomas.carddetails.Aadhaar.AadhaarCardDetails;
import com.cardmgnt.jocelinthomas.carddetails.Aadhaar.DataModel;
import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.DividerItemDecoration;
import com.cardmgnt.jocelinthomas.carddetails.EnterPassword;
import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;
import com.cardmgnt.jocelinthomas.carddetails.Pan.PanCardAdapter;
import com.cardmgnt.jocelinthomas.carddetails.Pan.PanCardDetails;
import com.cardmgnt.jocelinthomas.carddetails.Pan.PanModel;
import com.cardmgnt.jocelinthomas.carddetails.Passport.PassportDetailsActivity;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

//import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class VoterID_Details extends AppCompatActivity {


    DBHelper database;
    RecyclerView recyclerView;
    VoterAdapter recycler;
    List<VotersModel> datamodel;
    private CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;
    private static final String SHOWCASE_ID = "Simple Showcase";

    boolean shouldExecuteOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_id__details);
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


        datamodel = new ArrayList<VotersModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        database = new DBHelper(this);
        datamodel = database.getvoterdata();
        recycler = new VoterAdapter(this, datamodel);

        Log.i("HIteshdata", "" + datamodel);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recycler);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoterID_Details.this, VoterIDActivity.class);
                startActivity(intent);
            }
        });
        showMaterialCase(100);

    }

/*    @Override
    protected void onResume() {
        super.onResume();
        if (shouldExecuteOnResume) {

            SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
            String password = sharedPreferences.getString("password", "");

            startActivity(new Intent(VoterID_Details.this, EnterPassword.class));
        } else {
            shouldExecuteOnResume = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }*/

    private void showMaterialCase(int i) {
        new MaterialShowcaseView.Builder(VoterID_Details.this) // instantiate the material showcase view using Builder
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
        Intent intent = new Intent(VoterID_Details.this, HomeActivity.class);
        startActivity(intent);
    }
}
