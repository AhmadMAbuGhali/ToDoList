package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class tasklist extends AppCompatActivity {
    Button backtolist,searchB;
    TextView TaskParent,delete,TaskHeader;
    EditText CNT;
    RecyclerView recyclerTask;
    public tasklist_adapter adapter;
    public ArrayList<tasklist_item> tasklist_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);
        backtolist =findViewById(R.id.backtolist);
        searchB =findViewById(R.id.searchB);
        delete =findViewById(R.id.delete);
        TaskParent =findViewById(R.id.TaskParent);
        TaskHeader =findViewById(R.id.TaskHeader);
        CNT =findViewById(R.id.CNT);
        recyclerTask =findViewById(R.id.recyclerTask);


        backtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tasklist.this,listToDo.class);
                startActivity(intent);
                finish();
            }
        });

        tasklist_items =new ArrayList<>();
        tasklist_items.add(new tasklist_item("Personal"));
        adapter = new tasklist_adapter(tasklist_items);
        recyclerTask.setAdapter(adapter);
        recyclerTask.setLayoutManager(new LinearLayoutManager(this));
        recyclerTask.setHasFixedSize(true);

        CNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasklist_item tasklist_item =new tasklist_item();
                tasklist_item.setTaskName(CNT.getText().toString().trim());
                tasklist_items.add(tasklist_item);
                adapter.notifyDataSetChanged();
            }
        });

    }
}