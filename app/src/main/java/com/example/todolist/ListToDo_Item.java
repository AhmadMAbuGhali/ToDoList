package com.example.todolist;

import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class ListToDo_Item {
    String ListName, TaskNum;

    public ListToDo_Item(){

    }
    public ListToDo_Item(String listName, String taskNum) {
        ListName = listName;
        TaskNum = taskNum;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public String getTaskNum() {
        return TaskNum;
    }

    public void setTaskNum(String taskNum) {
        TaskNum = taskNum;
    }
}
