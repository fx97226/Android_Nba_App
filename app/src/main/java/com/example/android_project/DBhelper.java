package com.example.android_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(email text primary key, password text, username text, firstname text, lastname text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");

    }

    // insert into database

    public boolean ins(String firstname, String lastname, String username, String email, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);

        long i = db.insert("user",null,contentValues);
        if (i==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkemail(String email){

        SQLiteDatabase db= this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from user where email=?", new String[] {email});
        if (c.getCount()>0){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean check_credentials(String email, String password){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?",new String[]{email,password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }
}
