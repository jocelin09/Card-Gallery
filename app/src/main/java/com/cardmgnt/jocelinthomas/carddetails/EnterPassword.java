package com.cardmgnt.jocelinthomas.carddetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.Home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class EnterPassword extends AppCompatActivity {

    Button submit;
    String password;


    //
    private FrameLayout mFrameCloseKeyboard;

    private FrameLayout mFrameNumber1;
    private FrameLayout mFrameNumber2;
    private FrameLayout mFrameNumber3;
    private FrameLayout mFrameNumber4;
    private FrameLayout mFrameNumber5;
    private FrameLayout mFrameNumber6;
    private FrameLayout mFrameNumber7;
    private FrameLayout mFrameNumber8;
    private FrameLayout mFrameNumber9;
    private FrameLayout mFrameNumber0;
    private FrameLayout mFrameNumberDeleteSpace;
    private FrameLayout mFrameNumberNext;

    private List<String> mListPin;

    private EditText mPin1;
    private EditText mPin2;
    private EditText mPin3;
    private EditText mPin4;

//    private EditText edtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

      //  getSupportActionBar().hide();

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", 0);
        password = sharedPreferences.getString("password", "");


        mFrameCloseKeyboard = (FrameLayout) findViewById(R.id.frameLayout_close_keyboard);

        mFrameNumber1 = (FrameLayout) findViewById(R.id.frameLayout_number1);
        mFrameNumber2 = (FrameLayout) findViewById(R.id.frameLayout_number2);
        mFrameNumber3 = (FrameLayout) findViewById(R.id.frameLayout_number3);
        mFrameNumber4 = (FrameLayout) findViewById(R.id.frameLayout_number4);
        mFrameNumber5 = (FrameLayout) findViewById(R.id.frameLayout_number5);
        mFrameNumber6 = (FrameLayout) findViewById(R.id.frameLayout_number6);
        mFrameNumber7 = (FrameLayout) findViewById(R.id.frameLayout_number7);
        mFrameNumber8 = (FrameLayout) findViewById(R.id.frameLayout_number8);
        mFrameNumber9 = (FrameLayout) findViewById(R.id.frameLayout_number9);
        mFrameNumber0 = (FrameLayout) findViewById(R.id.frameLayout_number0);
        mFrameNumberDeleteSpace = (FrameLayout) findViewById(R.id.frameLayout_deletePin);
        mFrameNumberNext = (FrameLayout) findViewById(R.id.frameLayout_next);

        mPin1 = (EditText) findViewById(R.id.textView_pin1);
        mPin2 = (EditText) findViewById(R.id.textView_pin2);
        mPin3 = (EditText) findViewById(R.id.textView_pin3);
        mPin4 = (EditText) findViewById(R.id.textView_pin4);


        mFrameCloseKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
            }
        });

        setPin();



    }

    private void setPin() {


        mListPin = new ArrayList<>();

        mFrameNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("1");
                    conditioningPinButton();
                }else{

                }

            }
        });

        mFrameNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("2");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("3");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("4");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("5");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("6");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("7");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("8");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("9");
                    //Toast.makeText(InputPinActivity.this, "Size : "+ mListPin.size(), Toast.LENGTH_LONG).show();
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("0");
                    conditioningPinButton();
                }else{

                }
            }
        });

        /**
         * Delete pin one by one, after that, change the background of indicator
         */
        mFrameNumberDeleteSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mListPin.size() != 0){
                    mListPin.remove(mListPin.size()-1);
                    if(mListPin.size()==3){
                        mPin4.setBackgroundResource(R.drawable.non_selected_item);
                    }else if(mListPin.size()==2){
                        mPin3.setBackgroundResource(R.drawable.non_selected_item);
                    }else if(mListPin.size()==1){
                        mPin2.setBackgroundResource(R.drawable.non_selected_item);
                    }else if(mListPin.size()==0){
                        mPin1.setBackgroundResource(R.drawable.non_selected_item);
                    }
                }
            }
        });

        mFrameNumberNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //print your PIN
                String yourPin="";
                for(int i=0; i<mListPin.size(); i++){
                    yourPin += mListPin.get(i);
                }
                System.out.print("Your PIN : " + yourPin);


                if(mListPin.size()==4 && yourPin.equals(password)){
                    Intent intent = new Intent(EnterPassword.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    return;

                }
                else{
                    Toast.makeText(EnterPassword.this, R.string.msg_complete_your_pin, Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void conditioningPinButton() {
        if(mListPin.size()==1){
            mPin1.setBackgroundResource(R.drawable.selecteditem);
        }else if(mListPin.size()==2){
            mPin2.setBackgroundResource(R.drawable.selecteditem);
        }else if(mListPin.size()==3){
            mPin3.setBackgroundResource(R.drawable.selecteditem);
        }else if(mListPin.size()==4){
            mPin4.setBackgroundResource(R.drawable.selecteditem);
        }
    }

}
