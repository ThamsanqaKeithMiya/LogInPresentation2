package com.example.loginpresentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class appointmentpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentpage);
        // here is the code to retrieve the values from the previous activity
        Intent intent= getIntent();
        // creating variables to intercept the values
        String fnametxt = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        String lnametxt = intent.getStringExtra(MainActivity.et_lname);
        String emailtxt = intent.getStringExtra(MainActivity.et_email);

    }
}