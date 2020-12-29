package com.example.todolist.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Activity.TaskItem;
import com.example.todolist.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolider> {
    Context context;
    public List<TaskItem> taskItems =new ArrayList<>();
    FirebaseAuth mAuth;

    public TaskAdapter(Context context, List<TaskItem> taskItems) {
        this.context = context;
        this.taskItems = taskItems;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskAdapterViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskAdapterViewHolider holder, int position) {
        holder.setData(taskItems.get(position));
        TaskItem taskItem = taskItems.get(position);
        if(taskItem.getCheeked()==true){
            holder.checkBoxTAsk.setChecked(true);
            holder.checkBoxTAsk.setPaintFlags(holder.checkBoxTAsk.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.taskName.setText(taskItem.getTaskName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TaskItem.class);
                intent.putExtra("taskName",taskItem.getTaskName());
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

                TaskItem newTask= new TaskItem();
                newTask.setTaskName(taskItem.getTaskName());
                newTask.setCheeked(isChecked);

                newTask.setTaskId(taskId);
                FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Lists").child(ListId).child("Tasks").child(taskId).setValue(newTask);

                taskItem.setCheeked(isChecked);
                holder.checkBoxTAsk.setSelected(isChecked);
                if(isChecked){
                    holder.checkBoxTAsk.setText(taskItem.getTaskName());
                    holder.checkBoxTAsk.setPaintFlags( holder.checkBoxTAsk.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else {
                    holder.checkBoxTAsk.setText(taskItem.getTaskName());
                    holder.checkBoxTAsk.setPaintFlags( holder.checkBoxTAsk.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }

        });








    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TaskAdapterViewHolider extends RecyclerView.ViewHolder {
        TextView taskName;
        TextView delete;
        CheckBox checkBoxTAsk;


        public TaskAdapterViewHolider(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            checkBoxTAsk = itemView.findViewById(R.id.checkboxTask);
            delete = itemView.findViewById(R.id.delete);

        }

        public void setData(final TaskItem tasklist_item) {
            checkBoxTAsk.setText(tasklist_item.getTaskName());
            checkBoxTAsk.setSelected(tasklist_item.getCheeked());
    }
}
}
