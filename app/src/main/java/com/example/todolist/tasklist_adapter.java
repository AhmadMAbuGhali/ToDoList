package com.example.todolist;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class tasklist_adapter extends RecyclerView.Adapter<tasklist_adapter.tasklist_adapterViewHolider> {
    public List<tasklist_item> tasklist;

public tasklist_adapter(ArrayList<tasklist_item> tasklist_items){
    this.tasklist= tasklist_items;
}




    @NonNull
    @Override
    public tasklist_adapterViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tasklist_item,parent,false);

        return new tasklist_adapterViewHolider(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull tasklist_adapterViewHolider holder, int position) {
    com.example.todolist.tasklist_item tasklistItem = tasklist.get(position);
    holder.taskName.setText(tasklistItem.getTaskName());


    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }

    public class tasklist_adapterViewHolider extends RecyclerView.ViewHolder{
    TextView taskName;
        public tasklist_adapterViewHolider(@NonNull View itemView) {
            super(itemView);
            taskName =itemView.findViewById(R.id.taskName);

        }
    }
}
