package com.example.todolist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todolist.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class TaskItem {
     String taskName;
     Boolean isCheeked;
     String taskId;
     String taskListid;

    public TaskItem(String taskName, Boolean isCheeked, String taskId, String taskListid) {
        this.taskName = taskName;
        this.isCheeked = isCheeked;
        this.taskId = taskId;
        this.taskListid = taskListid;
    }

    public TaskItem(){}


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getCheeked() {
        return isCheeked;
    }

    public void setCheeked(Boolean cheeked) {
        isCheeked = cheeked;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskListid() {
        return taskListid;
    }

    public void setTaskListid(String taskListid) {
        this.taskListid = taskListid;
    }
}