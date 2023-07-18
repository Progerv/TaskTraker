package ru.sysdyn.tasktraker.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.sysdyn.tasktraker.data.database.TaskDao
import ru.sysdyn.tasktraker.data.database.TaskDatabase
import ru.sysdyn.tasktraker.data.model.TaskEntity

class TasksRepositoryImpl(
    context: Context,
    private val backgroundDispatcher: CoroutineDispatcher
) : TaskRepository {

    private val taskDao: TaskDao

    init {
        val database = TaskDatabase.getInstance(context, backgroundDispatcher)
        taskDao = database!!.taskDao()
    }

    override suspend fun insert(task: TaskEntity) {
        withContext(backgroundDispatcher) {
            taskDao.insertTask(task)
        }
    }

    override suspend fun update(task: TaskEntity) {
        withContext(backgroundDispatcher) { taskDao.updateTask(task) }
    }

    override suspend fun delete(task: TaskEntity) {
        withContext(backgroundDispatcher) { taskDao.deleteTask(task) }
    }

    override fun getAllTasks(): LiveData<List<TaskEntity>> {
        return taskDao.getTasks()
    }
}