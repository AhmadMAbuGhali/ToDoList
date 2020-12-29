package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class tasklist_adapter extends RecyclerView.Adapter<tasklist_adapter.tasklist_adapterViewHolider> {
    Context context;
    public List<tasklist_item> tasklist =new ArrayList<>();
    FirebaseAuth mAuth;

    public tasklist_adapter(Context applicationContext, ArrayList<tasklist_item> tasklist_items) {
        this.context = context;
        this.tasklist = tasklist_items;
    }

    public tasklist_adapter(ArrayList<tasklist_item> tasklist_items) {
    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    public class tasklist_adapterViewHolider extends RecyclerView.ViewHolder {
        TextView taskName;
        TextView delete;
        CheckBox checkBoxTAsk;


        public tasklist_adapterViewHolider(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            checkBoxTAsk = itemView.findViewById(R.id.checkboxTask);
            delete = itemView.findViewById(R.id.delete);

        }

        public void setData(final tasklist_item tasklist_item) {
            checkBoxTAsk.setText(tasklist_item.getTaskName());
            checkBoxTAsk.setSelected(tasklist_item.getCheeked());
        }
    }
//    private void getListSize(String listName) {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Lists").child(listName);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot s : snapshot.getChildren()) {
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });}

    @NonNull
    @Override
    public tasklist_adapterViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tasklist_item, parent, false);

        return new tasklist_adapterViewHolider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tasklist_adapterViewHolider holder, int position) {
        holder.setData(tasklist.get(position));
        tasklist_item tasklistItem = tasklist.get(position);
        if(tasklistItem.getCheeked()==true){
            holder.checkBoxTAsk.setChecked(true);
            holder.checkBoxTAsk.setPaintFlags(holder.checkBoxTAsk.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.taskName.setText(tasklistItem.getTaskName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,tasklist.class);
                intent.putExtra("taskName",tasklistItem.getTaskName());
                context.startActivity(intent);

            }
        });
        holder.checkBoxTAsk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                String ListId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").push().getKey();
                String taskId = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks").push().getKey();

                tasklist_item newTask= new tasklist_item();
                newTask.setTaskName(tasklistItem.getTaskName());
                newTask.setCheeked(isChecked);

                newTask.setTaskId(taskId);
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks").child(taskId).setValue(newTask);

                tasklistItem.setCheeked(isChecked);
                holder.checkBoxTAsk.setSelected(isChecked);
                if(isChecked){
                    holder.checkBoxTAsk.setText(tasklistItem.getTaskName());
                    holder.checkBoxTAsk.setPaintFlags( holder.checkBoxTAsk.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else {
                    holder.checkBoxTAsk.setText(tasklistItem.getTaskName());
                    holder.checkBoxTAsk.setPaintFlags( holder.checkBoxTAsk.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }

        });



    }


}
