package com.cardmgnt.jocelinthomas.carddetails.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.DividerItemDecoration;
import com.cardmgnt.jocelinthomas.carddetails.EnterPassword;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayoutManager lLayout;
    NavigationView navigationView;
    boolean shouldExecuteOnResume;
   // private static final int REQUEST_CAMERA = 0;
    /**
     * Id to identify a contacts permission request.
     */
    private static final int REQUEST_CONTACTS = 1;

    /**
     * Permissions required to read and write contacts.
     */
    private static String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

    private int STORAGE_PERMISSION_CODE = 23;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        List<ItemObject> rowListItem = getAllItemList();

        // Create the recyclerview.
        RecyclerView carRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // Create the grid layout manager with 2 columns.
        DividerItemDecoration itemDecor = new DividerItemDecoration(carRecyclerView.getContext());
        carRecyclerView.addItemDecoration(itemDecor);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);

        // Set layout manager.
        carRecyclerView.setLayoutManager(gridLayoutManager);

        // Create car recycler view data adapter with car item list.
        RecyclerViewAdapter carDataAdapter = new RecyclerViewAdapter(HomeActivity.this, rowListItem);
        // Set data adapter.
        carRecyclerView.setAdapter(carDataAdapter);
      /*  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {
            // Camera permission has not been granted.

            requestCameraPermission();

        } else {

            // Camera permissions is already available, show the camera preview.
           // Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
            //showCameraPreview();
           // Intent intent = new Intent(HomeActivity.this, ScanDocument.class);
           // startActivity(intent);
        }*/



        if(isReadStorageAllowed()){

        }
        else
        {
            requestStoragePermission();
        }


    }

    /*private void requestStoragePermission() {
    }

    private boolean isReadStorageAllowed() {
    }*/






    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA))

        // if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},STORAGE_PERMISSION_CODE);
        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result=0;
        try{

            //ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            result= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) + ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        }
        catch(Exception e)

        {

            //If permission is granted returning true
        }
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //This method will be called when the user will tap on allow or deny
    @SuppressLint("Override")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                //Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops!! You just denied the permissions",Toast.LENGTH_LONG).show();
            }
        }
    }










//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (shouldExecuteOnResume) {
//
//            SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
//            String password = sharedPreferences.getString("password", "");
//
//            startActivity(new Intent(HomeActivity.this, EnterPassword.class));
//        } else {
//            shouldExecuteOnResume = true;
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }

    private List<ItemObject> getAllItemList() {

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("Aadhaar Card", R.drawable.newyork));
        allItems.add(new ItemObject("PAN Card", R.drawable.canada));
        allItems.add(new ItemObject("Passport", R.drawable.uk));
        allItems.add(new ItemObject("Voter ID", R.drawable.germany));
        allItems.add(new ItemObject("Driving License", R.drawable.germany));
        allItems.add(new ItemObject("Mediclaim", R.drawable.germany));
        allItems.add(new ItemObject("Credit Card", R.drawable.germany));
        allItems.add(new ItemObject("Debit Card", R.drawable.germany));

        return allItems;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //  super.onBackPressed();
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);

            // alertDialogBuilder.setTitle("Exit");
            alertDialogBuilder.setMessage("Are you sure you want to exit?");
            //alertDialogBuilder.setIcon(R.drawable.pwd);
            alertDialogBuilder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //HomeActivity.this.finish();
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            // create alert dialog
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       // navigationView.getMenu().getItem(0).setChecked(true);
        // navigationView.setCheckedItem(R.id.home);
        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.scan) {
            // Check if the Camera permission is already available.

            Intent intent = new Intent(HomeActivity.this, ScanDocument.class);
            startActivity(intent);
        } else if (id == R.id.rateus) {

            Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
            }

        } else if (id == R.id.feedback) {
            //only gmail client should open.
            try {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("message/rfc822");
                Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"jocelinthomas94@gmail.com"});
                Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                Email.putExtra(Intent.EXTRA_TEXT, "" + "");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_share) {

            try {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                StringBuilder sb = new StringBuilder();
                sb.append("Hey,I am using this CardHub App. I like this and I want you to check it out.");
                sb.append("https://play.google.com/store/apps/details?id=" + this.getPackageName());
                //sharingIntent.addFlags(activityfl.ClearWhenTaskReset);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "CardHub");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                startActivity(Intent.createChooser(sharingIntent, "CardHub"));
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (id == R.id.privacy) {

            //https://termsfeed.com/privacy-policy/f66629a8aaa2db0b1dcb9afd69020be5

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_CONTACTS)) {

            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example, if the request has been denied previously.
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        } else {
            // Contact permissions have not been granted yet. Request them directly.
            ActivityCompat.requestPermissions(this,PERMISSIONS_CONTACT , REQUEST_CONTACTS);
        }
    }


}
