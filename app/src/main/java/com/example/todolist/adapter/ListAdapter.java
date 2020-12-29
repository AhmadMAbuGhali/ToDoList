package com.example.todolist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Activity.ListsItem;
import com.example.todolist.R;
import com.example.todolist.classes.Lists;
import com.example.todolist.classes.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListAdapterViewHolider> {
        Context context;
        int listSize;
        public List<ListsItem> listsItems;

    public ListAdapter(Context context, List<ListsItem> listsItems) {
        this.context = context;
        this.listsItems = listsItems;
    }

    @NonNull
    @Override
    public ListAdapter.ListAdapterViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lists_item,parent,false);
        return new ListAdapterViewHolider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListAdapterViewHolider holder, int position) {
        ListsItem item = listsItems.get(position);
        holder.ListName.setText(item.getListName());
        int size = getListSize(item.getListName());
        holder.TaskNum.setText(size+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, Tasks.class);
                intent.putExtra("ListName",item.getListName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listsItems.size();
    }

    public class ListAdapterViewHolider extends RecyclerView.ViewHolder {
        TextView ListName,TaskNum;
        public ListAdapterViewHolider(@NonNull View itemView) {
            super(itemView);
            ListName =itemView.findViewById(R.id.ListName);
            TaskNum =itemView.findViewById(R.id.TaskNum);

        }
    }
    private int getListSize(String listName) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child("tasks");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    listSize += 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listSize;
    }

}
