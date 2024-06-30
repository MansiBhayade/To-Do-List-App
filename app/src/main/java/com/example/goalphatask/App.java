package com.example.goalphatask;

import android.app.Application;

import com.example.goalphatask.Dao.TaskDao;
import com.example.goalphatask.Dao.TaskDatabase;

public class App extends Application {
    private static App instance;
    private TaskDao taskDao;


    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();

        TaskDatabase db = TaskDatabase.getInstance(getApplicationContext());
        taskDao = db.taskDao();

    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

}
