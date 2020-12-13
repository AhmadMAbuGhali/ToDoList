package com.example.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class listToDo extends AppCompatActivity {
        Button back;
        TextView Header,ListText;
        EditText CNL,search;
        RecyclerView recycler;
         private ListToDo_Adapter adapter;
     private ArrayList <ListToDo_Item> listToDo_items;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_do);
        back =findViewById(R.id.back);
        Header =findViewById(R.id.Header);
        ListText =findViewById(R.id.ListText);
        CNL =findViewById(R.id.CNL);
        search =findViewById(R.id.search);
        recycler =findViewById(R.id.recycler);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listToDo.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        listToDo_items = new ArrayList<>();
        listToDo_items.add(new ListToDo_Item("Personal","3task"));
        adapter =new ListToDo_Adapter(listToDo_items);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        CNL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListToDo_Item listToDo_item =new ListToDo_Item();
                listToDo_item.setListName(CNL.getText().toString().trim());
                listToDo_item.setTaskNum("0");
                listToDo_items.add(listToDo_item);
                adapter.notifyDataSetChanged();
            }
        });


    }


}