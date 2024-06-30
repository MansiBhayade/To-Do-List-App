package com.example.goalphatask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Delete;


import com.example.goalphatask.Dao.Task;
import com.example.goalphatask.Dao.TaskDao;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends AppCompatActivity {


    private EditText newTaskEditText;
    private Button saveButton;
    private Button cancelButton;
    TaskDao taskDao = App.getInstance().getTaskDao();

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    {
        setContentView(R.layout.new_task);

        newTaskEditText = findViewById(R.id.newTaskText);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskText = newTaskEditText.getText().toString();
                if (!taskText.isEmpty()) {
                    Task task = new Task(taskText, 0); // Status 0 for not completed task
                    taskDao.insertTask(task);
                    // Start MainActivity
                    Intent intent = new Intent(AddNewTask.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(AddNewTask.this, "Please enter your task to save", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewTask.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    }
}
