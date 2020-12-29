package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolider>{
Context context;

public List<SearchItem> searchItems;


    @NonNull
    @Override
    public SearchAdapterViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_item,parent,false);

        return new SearchAdapterViewHolider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapterViewHolider holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class  SearchAdapterViewHolider extends RecyclerView.ViewHolder{

        public SearchAdapterViewHolider(@NonNull View itemView){
            super(itemView);
        }
    }
}