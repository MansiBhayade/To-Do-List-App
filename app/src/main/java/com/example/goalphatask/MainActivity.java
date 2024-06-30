package com.example.goalphatask;




import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goalphatask.Dao.Task;
import com.example.goalphatask.Dao.TaskDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView tasksRecyclerView;
    private FloatingActionButton fab;

    private TextView placeholderTextView;

    private TaskAdapter taskAdapter;
    TaskDao taskDao = App.getInstance().getTaskDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);

        placeholderTextView = findViewById(R.id.placeholderTextView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setTitleTextColor(Color.WHITE);
        }

        tasksRecyclerView = findViewById(R.id.recyclerview);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewTask.class);
                startActivity(intent);
            }
        });

        // Retrieve tasks from database and display them in the RecyclerView
        retrieveTasks();

    }

    private void retrieveTasks() {
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(new ArrayList<Task>(), this);
        tasksRecyclerView.setAdapter(taskAdapter);
        List<Task> tasks = taskDao.getAllTasks();
        if (tasks.isEmpty()) {
                    // If the task list is empty, show the placeholder TextView and hide the RecyclerView
                    placeholderTextView.setVisibility(View.VISIBLE);
                    tasksRecyclerView.setVisibility(View.GONE);
                } else {
                    // If the task list is not empty, display the tasks in the RecyclerView and hide the placeholder TextView
                    placeholderTextView.setVisibility(View.GONE);
                    tasksRecyclerView.setVisibility(View.VISIBLE);
                    taskAdapter.setTasks(tasks);
                }
    }

}