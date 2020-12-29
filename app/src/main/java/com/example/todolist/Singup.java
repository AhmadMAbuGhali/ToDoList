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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Singup extends AppCompatActivity {
 TextView CPTS,AHA ,loginP;
 Button CYP;
 EditText Yname ,Spassword,Semail ;
 FirebaseAuth mAuth;
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
        mAuth = FirebaseAuth.getInstance();

        loginP.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Singup.this,Login.class);
            startActivity(intent);
        }
    });
//    if (mAuth.getCurrentUser() != null){
//        startActivity(new Intent(getApplicationContext(),listToDo.class));
//        finish();
//    }

    CYP.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name =Yname.getText().toString().trim();
            String email =Semail.getText().toString().trim();
            String password = Spassword.getText().toString().trim();

            //Check if data not null
            if (TextUtils.isEmpty(name)){
                Yname.setError("name is Required");
                return;
            }
            if (TextUtils.isEmpty(email)){
                Semail.setError("Email is Required");
                return;
            }
            if (TextUtils.isEmpty(password)){
                Spassword.setError("Password is Required");
            return;
            }
            if (password.length()<6){
                Spassword.setError("Password is Short  mack it more 6 Charavter");
                return;
            }
            // register user in firebase
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){


                        startActivity(new Intent(getApplicationContext(),listToDo.class));
                        finish();
                        FirebaseUser user = mAuth.getCurrentUser();
                        String uid = user.getUid();
                        Map<String,Object> data=new HashMap<>();
                        data.put("uid",uid);
                        FirebaseDatabase.getInstance().getReference("Users").child(uid).setValue(data).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Singup.this,"Successfully sing up",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        Toast.makeText(Singup.this,"Error sing up"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    });






    }


}