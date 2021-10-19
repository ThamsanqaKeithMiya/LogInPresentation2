package com.example.loginpresentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;
    String firstname, lastname, email, password, passwordagain; //The passwordagain variable can be used to ensure that a user has entered the intended password when signing up. This is just error catching.
    Button login, signup1, signup2, cancel; //These are variables for all of the buttons in both User Interfaces. Signup1 is the signup button on the login page and signup2 is the signup button on the signup page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}