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
    EditText password; //variable will hold the password
    EditText passwordAgain; //The 'passwordAgain' variable can be used to ensure that a user has entered the intended password when signing up. This is just error catching.

    //These are variables for all of the buttons in both User Interfaces. Signup1 is the signup button on the login page and signup2 is the signup button on the signup page
    //Button login; This variable is used in the Log In page
    Button signup2;
    //Button cancel; This variable is used in the Log In page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //First we have to point all the variables created above to their allocated controls via ID
        firstname = findViewById(R.id.fname);
        lastname = findViewById(R.id.lname);
        email = findViewById(R.id.signupemail);
        password = findViewById(R.id.signuppassword);
        passwordAgain = findViewById(R.id.signuppasswordagain);

        DB = new DBHelper(this); //instantiating the DBHelper to achieve connection with database

        //To add a member into the data base we need to insert their data into the database. Therefore a SignUp button will be made to insert the user's details into the database.
        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Assigning the data from the Edittext fields from the UI into variables that will be referenced in the insert method as parameters.
                String passwordTXT1 = password.getText().toString();
                String passwordTXT2 = passwordAgain.getText().toString();

                if (passwordTXT1 == passwordTXT2) { // This 'if' statement checks to see if the user has entered the same password in both password fields as a form of error checking. It prevents the data from being entered into the database if two different passwords are entered.
                    String fNameTXT = firstname.getText().toString();
                    String lNameTXT = lastname.getText().toString();
                    String emailTXT = email.getText().toString();
                    String passwordTXT = password.getText().toString();

                    Boolean checkInsertData = DB.insertuserdata(emailTXT, fNameTXT, lNameTXT, passwordTXT); // Boolean variable that will let us know whether the data was successfully entered into the database or not

                    //creating a message to notify user when they have successfully signed up or not
                    if (!checkInsertData) {
                        Toast.makeText(MainActivity.this, "Sign Up Unsuccessful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Sign Up Complete", Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "Password entries do not match. Please ensure you have entered the correct password in both password fields.", Toast.LENGTH_LONG).show();
            }
        });
    }
}