package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize the components
        final EditText etUser = findViewById(R.id.etUser);
        final EditText etAuthentication = findViewById(R.id.etAuthentication);
        Button btnAccess = findViewById(R.id.btnValidate);
        Button btnListAll = findViewById(R.id.btnListAll);
        Button btnRegister = findViewById(R.id.btnRegister);
        //Registering the user
        SqLiteClass db = new SqLiteClass(MainActivity.this, 1);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(db.Signin(etUser.getText().toString(), etAuthentication.getText().toString())){
                    Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                    etUser.setText("");
                    etAuthentication.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Verifying the access
        btnAccess.setOnClickListener(v -> {
            if(db.isAutenticated(etUser.getText().toString())){
                Toast.makeText(this, "Your key is: "+db.passwordData , Toast.LENGTH_SHORT).show();
                //Should update the status to "1"
                db.hasAccess(etUser.getText().toString());
                return;
            }
            else{
                Toast.makeText(MainActivity.this, "Key doesn't exist", Toast.LENGTH_SHORT).show();
            }
        });

        btnListAll.setOnClickListener(v -> {
            Intent intent = new Intent( MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }

}