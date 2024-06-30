package com.example.goalphatask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.goalphatask.Dao.TaskDao;


public class EditTaskActivity extends AppCompatActivity {

    private EditText editTextTask;

    TaskDao taskDao = App.getInstance().getTaskDao();

    private Button done_button;

    private String originalTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);


        editTextTask = findViewById(R.id.edit_task);
        done_button = findViewById(R.id.done_button);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        originalTask = getIntent().getStringExtra("task");
        editTextTask.setText(originalTask);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the edited task title from the EditText
                String editedTask = editTextTask.getText().toString().trim();

                if (!editedTask.equals(originalTask)) {
                    // Update the task in the database
                    taskDao.updateTask(originalTask, editedTask);
                }
                Intent intent = new Intent(EditTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

    }
}
