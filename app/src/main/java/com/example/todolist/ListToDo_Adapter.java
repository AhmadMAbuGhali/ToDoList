package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListToDo_Adapter  extends RecyclerView.Adapter<ListToDo_Adapter.ListToDo_AdapterViewHolider> {
   public List<ListToDo_Item> listtodo ;

    public ListToDo_Adapter(ArrayList<ListToDo_Item> listToDo_items) {
        this.listtodo =listToDo_items;
    }


    @NonNull
    @Override

    public ListToDo_AdapterViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_to_do_item,parent,false);

        return new ListToDo_AdapterViewHolider(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListToDo_AdapterViewHolider holder, int position) {

        com.example.todolist.ListToDo_Item item = listtodo.get(position);
       holder.ListName.setText(item.getListName());
       holder.TaskNum.setText(item.getTaskNum());


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
            TaskNum =itemView.findViewById(R.id.TaskNum);


        }
    }
}
