package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Search extends AppCompatActivity {
    Button backtoPage;
    TextView TaskHeader,result;
    EditText searchP;
    RecyclerView RecSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        backtoPage = findViewById(R.id.backtoPage);
        TaskHeader = findViewById(R.id.TaskHeader);
        result = findViewById(R.id.result);
        searchP = findViewById(R.id.searchP);
        RecSearch = findViewById(R.id.RecSearch);
    }
}