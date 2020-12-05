package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
            ImageView logoImg;
            TextView Logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5*1000);

                    Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e){

                }

            }
        }; thread.start();
    }
}