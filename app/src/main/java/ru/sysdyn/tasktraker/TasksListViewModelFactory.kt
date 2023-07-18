package ru.sysdyn.tasktraker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.sysdyn.tasktraker.data.repository.TaskRepository

class TasksListViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksListViewModel(repository) as T
    }
}