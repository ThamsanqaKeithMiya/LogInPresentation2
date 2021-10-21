package com.example.loginpresentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper DB; //creating instant of the class
    EditText firstname; // variable will hold the name of the user
    EditText email; //variable will hold their email address
    EditText lastname; //variable will hold the user's last(surname)
    EditText password; //vairiable will hold the password
    EditText passwordagain; //The passwordagain variable can be used to ensure that a user has entered the intended password when signing up. This is just error catching.

    //These are variables for all of the buttons in both User Interfaces. Signup1 is the signup button on the login page and signup2 is the signup button on the signup page
    Button login;
    Button signup2;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //First we have to point all the variables created above to their allocated controls via ID
        firstname = findViewById(R.id.fname);
        lastname = findViewById(R.id.lname);
        email = findViewById(R.id.signupemail);
        password = findViewById(R.id.signuppassword);


        DB = new DBHelper(this); //instantiating the DBHelper to achieve connection with database


    //To add a member into the data base we need to insert their data into the database. Therefore a SignUp butto will be made to insert the user's detials into the database.
        //The insert block of code follows

        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Assigning the data from the Edittext fields from the UI into variables that will be referenced in the insert method as parameterss.
                String fnameTXT = firstname.getText().toString();
                String lnameTXT = lastname.getText().toString();
                String emailTXT =email.getText().toString();
                String passwordTXT = password.getText().toString();

               Boolean checkinsertdata = DB.insertuserdata(emailTXT, fnameTXT, lnameTXT, passwordTXT);

               //creating a message to notify user when they have successfully signed up or not
               if (checkinsertdata == false)
               {
                   Toast.makeText(MainActivity.this, "Dign Up Unsuccessful", Toast.LENGTH_LONG).show();
               }else
               {
                   Toast.makeText(MainActivity.this, "Sign Up Complete", Toast.LENGTH_LONG).show();
               }
            }
        });

    }
}