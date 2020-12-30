package com.example.todolist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todolist.R;

import java.util.List;

public class ListsItem {
    String ListName, TaskNum ,ListId;


    public ListsItem(){

    }
    public ListsItem(String listName, String taskNum ) {
        ListName = listName;
        TaskNum = taskNum;
    }

    public String getListId() {
        return ListId;
    }

    public void setListId(String listId) {
        ListId = listId;
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

