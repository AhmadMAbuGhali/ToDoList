package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    TextView WelcomeBack,DHA,FP,CreateProfile;
    Button login;
    EditText Lpassword,Lemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login =findViewById(R.id.login);
        WelcomeBack =findViewById(R.id.WelcomeBack);
        DHA =findViewById(R.id.DHA);
        FP =findViewById(R.id.FP);
        CreateProfile =findViewById(R.id.CreateProfile);
        Lpassword =findViewById(R.id.Lpassword);
        Lemail =findViewById(R.id.Lemail);

        CreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent =new Intent(Login.this,Singup.class);
                startActivity(intent);
            }
        });


    }
}