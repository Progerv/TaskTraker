package ru.sysdyn.tasktraker.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.sysdyn.tasktraker.data.model.TaskEntity

interface TaskRepository {
    fun getAllTasks(): LiveData<List<TaskEntity>>
    suspend fun  insert(task: TaskEntity)
    suspend fun  update(task: TaskEntity)
    suspend fun  delete(task: TaskEntity)
}