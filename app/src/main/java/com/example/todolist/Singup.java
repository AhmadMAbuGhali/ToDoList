package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Singup extends AppCompatActivity {
 TextView CPTS,AHA ,loginP;
 Button CYP;
 EditText Yname ,Spassword,Semail ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        loginP = findViewById(R.id.loginP);
        CPTS =findViewById(R.id.CPTS);
        AHA =findViewById(R.id.AHA);
        CYP =findViewById(R.id.CYP);
        Yname =findViewById(R.id.Yname);
        Spassword =findViewById(R.id.Spassword);
        Semail =findViewById(R.id.Semail);

    loginP.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent =new Intent(Singup.this,Login.class);
            startActivity(intent);
        }
    });

    }

}