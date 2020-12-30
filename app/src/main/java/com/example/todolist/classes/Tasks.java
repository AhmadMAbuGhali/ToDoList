package com.example.todolist.classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Activity.ListsItem;
import com.example.todolist.Activity.Login;
import com.example.todolist.Activity.SignUp;
import com.example.todolist.Activity.TaskItem;
import com.example.todolist.R;
import com.example.todolist.adapter.TaskAdapter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {
    Button backtolist, searchB;
    TextView TaskParent, delete, TaskHeader;
    EditText CNT;
    RecyclerView recyclerTask;
    CheckBox checkboxTask;
    public TaskAdapter adapter;
    ListsItem item = new ListsItem();
    public ArrayList<TaskItem> taskItems= new ArrayList<>();
    FirebaseAuth mAuth;
    String ListId;
    ListsItem listsItem = new ListsItem();
    Lists lists = new Lists();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        backtolist = findViewById(R.id.backtolist);
        searchB = findViewById(R.id.searchB);
        delete = findViewById(R.id.delete);
        TaskParent = findViewById(R.id.TaskParent);
        TaskHeader = findViewById(R.id.TaskHeader);
        CNT = findViewById(R.id.CNT);
        recyclerTask = findViewById(R.id.recyclerTask);
        checkboxTask = findViewById(R.id.checkboxTask);
        String ListName = getIntent().getExtras().getString("ListName");
        ListId = getIntent().getExtras().getString("ListId");
        TaskParent.setText(ListName);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        backtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tasks.this, Lists.class);
                startActivity(intent);
                finish();
            }
        });


        CNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CNT.getText().toString().isEmpty()) {
                    String TaskId =  FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Tasks").push().getKey();
                    TaskItem taskItem = new TaskItem();
                    taskItem.setTaskName(CNT.getText().toString());
                    taskItem.setCheeked(false);
//                    taskItem.setTaskListid(ListId);
                    taskItem.setTaskId(TaskId);

                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks").child(TaskId).setValue(taskItem);
                    Toast.makeText(Tasks.this, "List add successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Tasks.this, "List don't add successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });




        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        taskItems.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            TaskItem task = snapshot.getValue(TaskItem.class);
                            taskItems.add(task);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });

        recyclerTask = findViewById(R.id.recyclerTask);
        recyclerTask.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(this, taskItems);
        recyclerTask.setAdapter(adapter);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").removeValue();
                Intent intent = new Intent(Tasks.this, Lists.class);
                startActivity(intent);
            }

        });

    }


}