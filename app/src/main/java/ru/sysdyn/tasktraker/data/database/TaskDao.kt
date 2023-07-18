package ru.sysdyn.tasktraker.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.sysdyn.tasktraker.data.model.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getTasks(): LiveData<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: TaskEntity): Long

    @Update
    suspend fun  updateTask(task: TaskEntity): Int

    @Delete
    suspend fun deleteTask(task: TaskEntity): Int
}