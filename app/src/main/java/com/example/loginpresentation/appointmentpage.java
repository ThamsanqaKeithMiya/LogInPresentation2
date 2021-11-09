package com.example.loginpresentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class appointmentpage extends AppCompatActivity {
    // creating the variables that will the values in the respective controls
    TextView tvDate;
    EditText etDate;
    DatePickerDialog.OnDateSetListener setListener;



    EditText etReason;
    Button btnMakeApp;
    Button btnViewData;
    Button btnBacktomain;

    DBHelper DB; //creating instant of the class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentpage);
        // here is the code to retrieve the values from the previous activity
        Intent intent= getIntent();
        // creating variables to intercept the values
        String fnametxt = intent.getStringExtra("EXTRA_TEXT");
        String lnametxt = intent.getStringExtra("et_lname");
        String emailtxt = intent.getStringExtra("et_email");

        //Assigning the variables to the respective controls
        /*tvDate = (TextView) findViewById(R.id.tv_date);*/
        etDate = (EditText) findViewById(R.id.et_date);
       TextView tvFname = (TextView) findViewById(R.id.tv_fname);
       TextView tvLname = (TextView) findViewById(R.id.tv_lname);
        etReason = (EditText) findViewById(R.id.et_Reason);
       TextView tvEmail = (TextView) findViewById(R.id.tv_email);
        btnMakeApp = (Button) findViewById(R.id.btn_MakeAppointment);
        btnViewData = (Button) findViewById(R.id.btn_ViewData);
        btnBacktomain = (Button) findViewById(R.id.btn_Backtomain);

        DB = new DBHelper(this); //instantiating the DBHelper to achieve connection with database

        //making sure the variables from the previous activity will show up in their respective controls
        tvFname.setText(fnametxt);
        tvLname.setText(lnametxt);
        tvEmail.setText(emailtxt);

        //assigning variables to the year, month and day
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DATE);

        /*tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        appointmentpage.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });*/
            //the functionality of the datetimepicker dialog
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month  = month+1;
                String date = day+"/"+month+"/"+year;
                tvDate.setText(date);
            }
        };
            //once we click the edittext control for date, the calendar will pop up
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        appointmentpage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        //button to insert the appointment details in the table created called AppointmentDet
        btnMakeApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String reasonTXT = etReason.getText().toString();
                String dateTXT = etDate.getText().toString();
                String emailTXT = tvEmail.getText().toString();
                String fnameTXT = tvFname.getText().toString();
                Boolean checkInsertData = DB.insertAppointmentDetails(emailTXT, fnameTXT, reasonTXT, dateTXT); // Boolean variable that will let us know whether the data was successfully entered into the database or not

                //creating a message to notify user when they have successfully signed up or not
                if (!checkInsertData) {
                    Toast.makeText(appointmentpage.this, "Booking Unsuccessful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(appointmentpage.this, "Appointment Booked!", Toast.LENGTH_LONG).show();


                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getData(); //This assigns the res variable to the value of the record that is of type cursor
                if(res.getCount()==0){
                    Toast.makeText(appointmentpage.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("email:"+res.getString(0)+"\n");
                    buffer.append("firstname:"+res.getString(1)+"\n");
                    buffer.append("reason:"+res.getString(2)+"\n");
                    buffer.append("date:"+res.getString(3)+"\n\n");
                }
                AlertDialog.Builder  builder = new AlertDialog.Builder(appointmentpage.this);
                builder.setCancelable(true);
                builder.setTitle("Appointments");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        //button will take us back to the main activity
        btnBacktomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openMainActivity();

            }
        });


    }
    public void openMainActivity()
    {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
}