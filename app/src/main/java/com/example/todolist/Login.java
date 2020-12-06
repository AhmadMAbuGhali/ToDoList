package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView WelcomeBack,DHA,FP,CreateProfile;
    Button login;
    EditText Lpassword,Lemail;
    FirebaseAuth mAuth;
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
        mAuth =FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =Lemail.getText().toString().trim();
                String password = Lpassword.getText().toString().trim();

                //Check if data not null

                if (TextUtils.isEmpty(email)){
                    Lemail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Lpassword.setError("Password is Required");
                    return;
                }
                if (password.length()<6){
                    Lpassword.setError("Password is Short  mack it more 6 Charavter");
                    return;
                }

                //authenticate the user

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),List.class));
                    }else{
                        Toast.makeText(Login.this,"Error Log in"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }

                    }
                });



            }

        });

        CreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent =new Intent(Login.this,Singup.class);
                startActivity(new Intent(getApplicationContext(),Singup.class));
            }
        });
    }
}