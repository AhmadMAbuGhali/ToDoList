package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listToDo extends AppCompatActivity {
    Button back;
    TextView Header, ListText;
    EditText CNL, search;
    RecyclerView recycler;
    private ListToDo_Adapter adapter;
    private ArrayList<ListToDo_Item> listToDo_items;
    FirebaseAuth mAuth;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_to_do);
        back = findViewById(R.id.back);
        Header = findViewById(R.id.Header);
        ListText = findViewById(R.id.ListText);
        CNL = findViewById(R.id.CNL);
        search = findViewById(R.id.search);
        recycler = findViewById(R.id.recycler);
        mAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listToDo.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        listToDo_items = new ArrayList<>();

        adapter = new ListToDo_Adapter(this, listToDo_items);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        CNL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CNL.getText().toString().isEmpty()) {
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user = mAuth.getCurrentUser();
                    String uid = user.getUid();
                    ListToDo_Item listToDo_item = new ListToDo_Item();
                    listToDo_item.setListName(CNL.getText().toString().trim());
                    String ListId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").push().getKey();
                    listToDo_item.setListId(ListId);
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).setValue(listToDo_item);
                    // listToDo_item.setTaskNum("Task") ;
                    // listToDo_items.add(listToDo_item);
                    // adapter.notifyDataSetChanged();
                } else {
                    Log.d("ttt", "test");

                }
            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listToDo_items.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ListToDo_Item List = snapshot.getValue(ListToDo_Item.class);
                            listToDo_items.add(List);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });


        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListToDo_Adapter(this, listToDo_items);
        recycler.setAdapter(adapter);


    }


}