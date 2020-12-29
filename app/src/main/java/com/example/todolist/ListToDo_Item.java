package com.example.todolist;

import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class ListToDo_Item {
    String ListName, TaskNum ,ListId,Tasks;

    public ListToDo_Item(String listName, String taskNum, String listId, String tasks) {
        ListName = listName;
        TaskNum = taskNum;
        ListId = listId;
        Tasks = tasks;
    }

    public ListToDo_Item(){

    }

    public String getListId() {
        return ListId;
    }

    public void setListId(String listId) {
        ListId = listId;
    }

    public String getTasks() {
        return Tasks;
    }

    public void setTasks(String tasks) {
        Tasks = tasks;
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
