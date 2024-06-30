package com.example.goalphatask.Dao;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "TaskList", indices = {
        @Index(value = {"id","Status","Task"}, unique = true)
})

public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Status")
    private int Status;

    @ColumnInfo(name = "Task")
    private String Task;

    public Task(String Task, int Status) {
        this.Task = Task;
        this.Status = Status;

    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String Task) {
        this.Task = Task;
    }


}



