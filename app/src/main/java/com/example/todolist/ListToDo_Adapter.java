package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListToDo_Adapter extends RecyclerView.Adapter<ListToDo_Adapter.ListToDo_AdapterViewHolider> {
    Context context;
    int sizeList = 0;

    public List<ListToDo_Item> listtodo;

    public ListToDo_Adapter(Context context, ArrayList<ListToDo_Item> listToDo_items) {
        this.context = context;
        this.listtodo = listToDo_items;
    }


    @NonNull
    @Override

    public ListToDo_AdapterViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_to_do_item, parent, false);

        return new ListToDo_AdapterViewHolider(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListToDo_AdapterViewHolider holder, int position) {

        com.example.todolist.ListToDo_Item item = listtodo.get(position);
        holder.ListName.setText(item.getListName());

        int size = getListSize(item.getListName());
        holder.TaskNum.setText(size+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,tasklist.class);
                intent.putExtra("listName",item.getListName()+" List");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {

        return listtodo.size();
    }

    public class ListToDo_AdapterViewHolider extends RecyclerView.ViewHolder {
        TextView ListName, TaskNum;

        public ListToDo_AdapterViewHolider(@NonNull View itemView) {
            super(itemView);
            ListName = itemView.findViewById(R.id.ListName);
            TaskNum = itemView.findViewById(R.id.TaskNum);


        }
    }
    private int getListSize(String listName) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Tasks");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    sizeList += 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return sizeList;
    }

}
