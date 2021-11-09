package com.example.loginpresentation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) { // This is the constructor that needs to be accompanied by the onCreate and onUpdate methods whenever creating a database class
        super(context, "Userdata.db", null, 1); //This method creates a database of version (or type) 1. "Userdata" is also the name of our database.
    }

    @Override //onCreate method is mandatory when creating a database class
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(email TEXT primary key, firstname TEXT, secondname TEXT, password TEXT)");//This is the SQL Query responsible for creating the "UserDetails" database. Columns for an email, firstName, secondName and password will be created.
        DB.execSQL("create Table AppointmentDet(email TEXT primary key, firstname TEXT, reason TEXT, date TEXT)");
    }

    @Override //onUpgrade method is mandatory when creating a database class as well
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");
        DB.execSQL("drop Table if exists AppointmentDet");
    }

    //Method to insert data into the database
    public boolean insertUserData(String email, String firstname, String lastname, String password) {
        SQLiteDatabase DB = this.getWritableDatabase(); //This assigns the SQLite database to the DB variable
        ContentValues contentValues = new ContentValues(); //This is going to allow us to add content to our database and place it in the respective columns
        contentValues.put("email", email);
        contentValues.put("firstname", firstname);
        contentValues.put("secondname", lastname);
        contentValues.put("password", password);
        long result = DB.insert("UserDetails", null, contentValues); // This is where we use the insert() method to actually place the values in the table so that a record can be correctly returned
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertAppointmentDetails(String email, String firstname, String Reason, String date) {
        SQLiteDatabase DB = this.getWritableDatabase(); //This assigns the SQLite database to the DB variable
        ContentValues contentValues = new ContentValues(); //This is going to allow us to add content to our database and place it in the respective columns
        contentValues.put("email", email);
        contentValues.put("firstname", firstname);
        contentValues.put("reason", Reason);
        contentValues.put("password", date);
        long result = DB.insert("AppointmentDet", null, contentValues); // This is where we use the insert() method to actually place the values in the table so that a record can be correctly returned
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Method to update the record that the cursor is on
    public boolean updateUserData(String email, String firstname, String lastname, String password) {
        SQLiteDatabase DB = this.getWritableDatabase(); //This assigns the SQLite database to the DB variable
        ContentValues contentValues = new ContentValues(); //This is going to allow us to add content to our database and place it in the respective columns
        contentValues.put("email", email);
        contentValues.put("firstname", firstname);
        contentValues.put("secondname", lastname);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from UserDetails where email = ?", new String[]{email});
        if (cursor.getCount()>0) {
            long result = DB.update("UserDetails", contentValues,"email=?",new String[]{email});
            cursor.close();
            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    //Method to delete a record from the database
    public boolean deleteUserData(String email) {
        SQLiteDatabase DB = this.getWritableDatabase(); //This assigns the SQLite database to the DB variable
        Cursor cursor = DB.rawQuery("Select * from UserDetails where email=?", new String[]{email}); //This assigns the cursor variable to the value of the record that the cursor is currently on
        if (cursor.getCount() > 0) { // This statement checks that there is indeed a record value that the cursor is on, just to make sure that there is actually a value to delete.
            long result = DB.delete("UserDetails", "email=?", new String[]{email});
            cursor.close();
            if (result == -1) {
                return false;
            } else
                return true;
        } else
            return false;
    }

    //Method responsible for fetching and viewing data from the database
    public Cursor getData() { //Whenever creating a method that makes use of a 'cursor', there usually isn't a need to make use of a parameter
        SQLiteDatabase DB = this.getWritableDatabase(); //This assigns the SQLite database to the DB variable
        Cursor cursor = DB.rawQuery("select * from UserDetails", null); //This assigns the cursor variable to the value of the record that the cursor is currently on
        return cursor;
    }

    public Cursor getAppointmentDet() { //Whenever creating a method that makes use of a 'cursor', there usually isn't a need to make use of a parameter
        SQLiteDatabase DB = this.getWritableDatabase(); //This assigns the SQLite database to the DB variable
        Cursor cursor = DB.rawQuery("select * from AppointmentDet", null); //This assigns the cursor variable to the value of the record that the cursor is currently on
        return cursor;
    }
}

//Research how to use #close() method to close the use of the cursor