package com.example.todolist.classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Activity.ListsItem;
import com.example.todolist.Activity.Login;
import com.example.todolist.Activity.SplashLogo;
import com.example.todolist.R;
import com.example.todolist.adapter.ListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lists extends AppCompatActivity {
    Button back;
    TextView Header, ListText, Logout;
    EditText CNL, search;
    RecyclerView recycler;
    private ListAdapter adapter;
    private ArrayList<ListsItem> listsItems = new ArrayList<>();
    FirebaseAuth mAuth;
    ListsItem listsItem = new ListsItem();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        back = findViewById(R.id.back);
        Header = findViewById(R.id.Header);
        ListText = findViewById(R.id.ListText);
        Logout = findViewById(R.id.Logout);
        CNL = findViewById(R.id.CNL);
        search = findViewById(R.id.search);
        recycler = findViewById(R.id.recycler);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        //back to Logo
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lists.this, SplashLogo.class);
                startActivity(intent);
                finish();
            }
        });
        //Create new Lists
        CNL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CNL.getText().toString().isEmpty()) {
                    listsItem.setListName(CNL.getText().toString());
                    String  ListId=   FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").push().getKey();
                    listsItem.setListId(ListId);
                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).setValue(listsItem);
                    Toast.makeText(Lists.this, "List add successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Lists.this, "List don't add successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listsItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ListsItem list = snapshot.getValue(ListsItem.class);
                    listsItems.add(list);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new com.example.todolist.adapter.ListAdapter(this, listsItems);
        recycler.setAdapter(adapter);

    }

}