package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NBA_login extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_b_a_login);
        getSupportActionBar().setTitle("Login");
        db= new DBhelper(this);
        e1 = findViewById(R.id.mail_log);
        e2 = findViewById(R.id.pwd_log);
        b1 = findViewById(R.id.Login);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                Boolean checkcred= db.check_credentials(s1,s2);
                if (checkcred){
                    Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), NBA_Home.class));

                }
                else {
                    Toast.makeText(getApplicationContext(),"ERROR Wrong credentials",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void btn_register(View view) {
        startActivity(new Intent(getApplicationContext(), NBA_Register.class));
    }


}