package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView List = findViewById(R.id.List); //Finding the list
        SqLiteClass sqLite = new SqLiteClass(MainActivity2.this, 1);//Where is my DB
        //Set the adapter on my "listAll"
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sqLite.listAll());
        //Fills the list
        List.setAdapter(adapter);
    }
}