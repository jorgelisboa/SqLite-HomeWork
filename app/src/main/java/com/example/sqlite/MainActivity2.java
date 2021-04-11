package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Screen items
        ListView List = findViewById(R.id.List); //Finding the list
        Button btnListAll = findViewById(R.id.btnListAll);
        Button btnListSome = findViewById(R.id.btnListSome);

        //Importing
        SqLiteClass sqLite = new SqLiteClass(MainActivity2.this, 1);//Where is my DB

        //My adapters
        //Adapters for all
        ArrayAdapter<String> adapterAll = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sqLite.listAll());//Set the adapter on my "listAll"
        //Adapters for authenticated
        ArrayAdapter<String> adapterAuth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sqLite.listAuth());//Set the adapter on my "listAll"


        //Buttons actions
        btnListAll.setOnClickListener(v -> {
            //Fills the list
            List.setAdapter(adapterAll);
        });

        btnListSome.setOnClickListener(v -> List.setAdapter(adapterAuth));

    }
}