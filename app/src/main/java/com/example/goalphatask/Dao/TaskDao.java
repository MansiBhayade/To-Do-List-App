package com.example.goalphatask.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Query("SELECT * FROM TaskList")
    List<Task> getAllTasks();

    @Delete
    void delete(Task task);


    @Query("DELETE FROM TaskList WHERE id = :taskId")
    void deleteTaskById(int taskId);


    @Query("UPDATE TaskList SET Task = :editedTask WHERE Task = :originalTask")
    void updateTask(String originalTask, String editedTask);

    @Query("UPDATE TaskList SET status = :newStatus WHERE id = :taskId")
    void updateTaskStatus(int taskId, int newStatus);


}
