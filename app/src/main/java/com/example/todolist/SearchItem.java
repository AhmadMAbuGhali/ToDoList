package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchItem {
    String  taskName;
    Boolean isCheeked;
    String taskId;

    public SearchItem(String taskName, Boolean isCheeked, String taskId) {
        this.taskName = taskName;
        this.isCheeked = isCheeked;
        this.taskId = taskId;
    }

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
}