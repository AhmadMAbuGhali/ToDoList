package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class tasklist extends AppCompatActivity {
    Button backtolist, searchB;
    TextView TaskParent, delete, TaskHeader;
    EditText CNT;
    RecyclerView recyclerTask;
    CheckBox checkboxTask;
    public tasklist_adapter adapter;
    public ArrayList<tasklist_item> tasklist_items;
    FirebaseAuth mAuth;
    ListToDo_Item listToDo_item = new ListToDo_Item();
    String ListId, taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);
        backtolist = findViewById(R.id.backtolist);
        searchB = findViewById(R.id.searchB);
        delete = findViewById(R.id.delete);
        TaskParent = findViewById(R.id.TaskParent);
        TaskHeader = findViewById(R.id.TaskHeader);
        CNT = findViewById(R.id.CNT);
        recyclerTask = findViewById(R.id.recyclerTask);
        checkboxTask = findViewById(R.id.checkboxTask);
        String ListName = getIntent().getExtras().getString("listName");
        TaskParent.setText(ListName);

        backtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tasklist.this, listToDo.class);
                startActivity(intent);
                finish();
            }
        });

        tasklist_items = new ArrayList<>();

        adapter = new tasklist_adapter(tasklist_items);
        recyclerTask.setAdapter(adapter);
        recyclerTask.setLayoutManager(new LinearLayoutManager(this));
        recyclerTask.setHasFixedSize(true);

        CNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CNT.getText().toString().isEmpty()) {
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();
                    String uid = user.getUid();
                    tasklist_item tasklist_item = new tasklist_item();
                    tasklist_item.setTaskName(CNT.getText().toString().trim());
//                    tasklist_items.add(tasklist_item);
//                    adapter.notifyDataSetChanged();
//                    ListToDo_Item listToDo_item = new ListToDo_Item();
                    tasklist_item.setCheeked(false);
                     ListId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").push().getKey();
                    listToDo_item.setListId(ListId);
                     taskId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Tasks").push().getKey();
                    String taskName = CNT.getText().toString().trim();
//                    listToDo_item.setTaskNum("Task") ;
                    tasklist_item.setTaskId(taskId);
                    tasklist_item.setTaskName(taskName);

                    //FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks").child(ListId).child(taskId).setValue(tasklist_item);
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks").setValue(tasklist_item);

                } else {
                    Log.d("ttt", "test1");
                }
            }
            //FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Tasks").child(ListId).child(taskId).setValue(tasklist_item);

        });
        tasklist_item task = new tasklist_item();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
//        String ListId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").push().getKey();
//        String taskId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks").push().getKey();
         taskId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Tasks").push().getKey();

        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Tasks").child(taskId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        tasklist_items.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            tasklist_item task = snapshot.getValue(tasklist_item.class);
                            tasklist_items.add(task);
                            recyclerTask = findViewById(R.id.recyclerTask);
                            recyclerTask.setLayoutManager(new LinearLayoutManager(tasklist.this));
                            adapter = new tasklist_adapter(tasklist.this, tasklist_items);
                            recyclerTask.setAdapter(adapter);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });




        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasklist_item task = new tasklist_item();
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                String ListId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").push().getKey();

                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks").child(taskId).removeValue();
            }

        });
    }


}