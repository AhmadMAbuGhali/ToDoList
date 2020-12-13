package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class tasklist_item  {
    String  taskName;
public tasklist_item(){

}

    public tasklist_item(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}