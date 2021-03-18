package com.example.android_project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_project.DBhelper;
import com.example.android_project.R;

public class NBA_Register extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6;
    DBhelper db;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_b_a__register);
        getSupportActionBar().setTitle("Register");

        db = new DBhelper(this);

        e1=findViewById(R.id.firstname);
        e2=findViewById(R.id.lastname);
        e3=findViewById(R.id.usnm);
        e4=findViewById(R.id.mail);
        e5=findViewById(R.id.pwd);
        e6=findViewById(R.id.confpwd);
        b1=findViewById(R.id.register);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = e6.getText().toString();

                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields is empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (s5.equals(s6)){

                        Boolean checkmail = db.checkemail(s5);
                        if (checkmail){
                            Boolean insert= db.ins(s1,s2,s3,s4,s5);
                            if (insert){
                                Toast.makeText(getApplicationContext(),"Registered successfuly",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), NBA_login.class));
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email already exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(),"Password missmatch",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}