package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqLiteClass extends SQLiteOpenHelper {
    //Creating DB object
    private final String createTable = "CREATE TABLE acesso ("
            + " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "chave VARCHAR(7) UNIQUE NOT NULL, "
            + "authentication VARCHAR(15) NOT NULL,"
            + "status VARCHAR(1) NOT NULL);";

    //Constructor
    public SqLiteClass(@Nullable Context context, int version) {
        super(context, "DB_test", null, version);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON"); //FK Enable
    }
    //Global Variables
    String passwordData;
    /*-----MY APP METHODS-----*/
    //SIGNING UP
    public boolean Signin (String chave, String authentication){
        //Using SQLiteDatabase to initialize the "connection" with database
        //getWritableDatabase selects the database app and opens the connection
        SQLiteDatabase database = getWritableDatabase();

        //Pass the values to the "insert"
        ContentValues valores = new ContentValues();
        valores.put("_id", (byte[]) null); //Column name, variable name
        valores.put("chave", chave); //Column name, variable name
        valores.put("authentication", authentication); //Column name, variable name
        valores.put("status", "0"); //Column name, variable name


        //Calling insert() to be verified by IF
        if (database.insert("acesso",null, valores) != -1){
            database.close();
            return true;
        }else{
            database.close();
            return false;
        }
    }

    //VERIFYING THE KEYS
    public boolean hasAccess(String chave){
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT chave FROM acesso", new String[]{ });

        if(cursor.moveToFirst()){
            //Recover password value
            String passwordData = cursor.getString(0); //Column authentication from select

            //Comparar DB data and the informed
            if(passwordData.equals(chave)){
                getWritableDatabase().close();
                return true;
            }else{
                getWritableDatabase().close();
                return false;
            }
        }else{
            getWritableDatabase().close();
            return false;
        }
    }
    //SHOWING AUTHENTICATION
    public boolean isAutenticated (String chave){
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT authentication FROM acesso WHERE chave = ?", new String[]{ chave });

        if(cursor.moveToFirst()){
            //Recover password value
            passwordData = cursor.getString(0); //Column authentication from select
            getWritableDatabase().close();
            return true;
        }else{
            getWritableDatabase().close();
            return false;
        }
    }
    //SETTING AUTHENTICATION
    public boolean hasStatus(String chave){
        SQLiteDatabase database = getWritableDatabase(); //Connection
        ContentValues values = new ContentValues();
        //Indicates the column and the variable
        values.put("status", "1");

        //Calling the Update and the Verifying
        if(database.update("acesso", values, "chave = ?", new String[]{chave}) != 0){
            database.close();
            return true;
        }else{
            database.close();
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);//Executes my DB
    }
    //Method that Upgrades my DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<String> listAll() {
        SQLiteDatabase db = getWritableDatabase(); //Open the connection
        //Creating the string vector
        List<String> completeList = new ArrayList<String>();

        //Using cursor to store select
        Cursor c = db.rawQuery("SELECT * FROM acesso;", new String[]{ });
        if(c.moveToFirst()){
            do{
                //Put the String in the ListView
                String content = "Key: " + c.getString(1)+ "\nAuthentication: " + c.getString(2) + "\nStatus: "+c.getString(3);
                //Add in the list
                completeList.add(content);
            }while (c.moveToNext());
        }
        db.close();
        //Return
        return completeList;
    }
    }

