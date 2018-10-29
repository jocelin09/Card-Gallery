package com.cardmgnt.jocelinthomas.carddetails;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;
//import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    LinearLayout l1,l2;
    Animation uptodown,downtoup;
    TextView t2;
    ImageView imageView;
   // private FirebaseAuth mAuth;


    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView) findViewById(R.id.image);
        t2 = (TextView) findViewById(R.id.texttext);

/*
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            //close this activity
            finish();
            //opening home activity
            Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
            startActivity(intent);
        }

        else{
            uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
            downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
            imageView.setAnimation(uptodown);
            t2.setAnimation(downtoup);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                }
            }, 2500);
        }*/


        SharedPreferences sharedPreferences  = getSharedPreferences("PREFS",0);
        password = sharedPreferences.getString("password","");


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (password.equals("")){
                    Intent intent = new Intent(SplashScreen.this, CreatePassword.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashScreen.this, EnterPassword.class);
                    startActivity(intent);
                    finish();
                }

            }
        },2000);




    }
}
