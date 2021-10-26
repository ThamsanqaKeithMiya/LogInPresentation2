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
        DB.execSQL("create Table UserDetails(email TEXT primary key, firstname TEXT, secondname TEXT, password TEXT)"); //This is the SQL Query responsible for creating the "UserDetails" database. Columns for an email, firstname, secondname and password will be created.
    }

    @Override //onUpgrade method is mandatory when creating a database class as well
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");
    }

    public boolean insertuserdata(String email, String firstname, String lastname, String password){
        SQLiteDatabase DB = this.getWritableDatabase(); //This assigns the SQLite database to the DB variable
        ContentValues contentValues = new ContentValues(); //This is going to allow us to add content to our database and place it in the respective columns
        contentValues.put("email", email);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("password", password);
        long result = DB.insert("UserDetails", null, contentValues); // This is where we use the insert() method to actually place the values in the table so that a record can be correctly returned
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteuserdata(String email){
        SQLiteDatabase DB = this.getWritableDatabase(); //This assigns the SQLite database to the DB variable
        Cursor cursor = DB.rawQuery("Select * from Userdetails where email=?", new String[]{email}); //This assigns the cursor variable to the value of the record that the cursor is currently on
        if (cursor.getCount()>0){ // This statement checks that there is indeed a record value that the cursor is on, just to make sure that there is actually a value to delete.
            long result = DB.delete("UserDetails", "email=?", new String[]{email});
            if(result==-1){
                return false;
            }
            else
                return true;
        }
        else
            return false;
    }
}
