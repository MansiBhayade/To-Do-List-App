package com.example.goalphatask;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalphatask.Dao.Task;
import com.example.goalphatask.Dao.TaskDao;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{


    TaskDao taskDao = App.getInstance().getTaskDao();

    private List<Task> taskList;





    private Context context;

    public TaskAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    public void setTasks(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    public List<Task> getTasks() {
        return taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskTextView.setText(task.getTask());
        holder.taskCheckBox.setChecked(task.getStatus() == 1);

        // Set the text color based on task status
        int textColor = task.getStatus() == 1 ? Color.GRAY : Color.BLACK;
        holder.taskTextView.setTextColor(textColor);

        holder.taskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the status of the task when the checkbox is clicked
                int newStatus = isChecked ? 1 : 0; // Set new status to 1 if checked, 0 if unchecked
                task.setStatus(newStatus);
                // Update the task in the database
                taskDao.updateTaskStatus(task.getId(), newStatus);

                // Show a toast message when the task is completed
                if (isChecked) {
                    Toast.makeText(context, "Yayy! You have completed the "+ task.getTask() + "task.", Toast.LENGTH_SHORT).show();
                }

                // Update the text color based on task status
                int textColor = newStatus == 1 ? Color.GRAY : Color.BLACK;
                holder.taskTextView.setTextColor(textColor);
            }
        });


    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }



    public void updateList(List<Task> newList) {
        taskList.clear();
        taskList.addAll(newList);
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {



        private CardView task_layout;

        private TextView taskTextView;
        private CheckBox taskCheckBox;



        private ImageView delete_icon, edit_icon  ;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            task_layout = itemView.findViewById(R.id.card_layout);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            taskCheckBox = itemView.findViewById(R.id.taskCheckBox);
            delete_icon = itemView.findViewById(R.id.delete_icon);
            edit_icon = itemView.findViewById(R.id.edit_icon);


            edit_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Get the task associated with this ViewHolder
                        Task task = taskList.get(position);
                        String edittask_text = task.getTask();
                        // Launch the EditTaskActivity to edit the task
                        Intent intent = new Intent(context, EditTaskActivity.class);
                        intent.putExtra("task", edittask_text);
//                        Toast.makeText(context, "edit"+ edittask_text, Toast.LENGTH_SHORT).show();
                        context.startActivity(intent);

                    }
                }
            });

            delete_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete this task ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int position = getBindingAdapterPosition();
                                    if (position != RecyclerView.NO_POSITION) {
                                        // Get the task associated with this ViewHolder
                                        Task task = taskList.get(position);
                                        taskDao.delete(task);
                                        deleteTask(position, task);
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing, simply dismiss the dialog
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            });
        }


        private void deleteTask(int position, Task task) {
            // Update your dataset to remove the task
            taskList.remove(position);
            // Notify the adapter that an item has been removed
            notifyItemRemoved(position);
            // Optionally, notify any observers of dataset changes
            notifyDataSetChanged();
        }
    }
    }
