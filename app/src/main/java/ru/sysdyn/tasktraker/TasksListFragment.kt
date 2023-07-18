package ru.sysdyn.tasktraker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import ru.sysdyn.tasktraker.data.model.TaskEntity
import ru.sysdyn.tasktraker.data.repository.TasksRepositoryImpl

class TasksListFragment : Fragment() {

    private lateinit var taskListViewModel: TasksListViewModel
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var recyclerView: RecyclerView
    private lateinit var placeholderContainer: ConstraintLayout
    private val taskAdapter = TasksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.tasks)
        placeholderContainer = view.findViewById(R.id.empty_placeholder)
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            CreateTaskDialog.newInstance(this.requireContext()) {
                taskListViewModel.insertTask(it)
            }
        }
        // 1. Создаём ViewModel через TasksListViewModelFactory
        // 2. Создаём репозиторий TasksRepositoryImpl
        viewModelFactory = TasksListViewModelFactory(
            TasksRepositoryImpl(requireContext(), Dispatchers.IO)
        )
        taskListViewModel =
            ViewModelProvider(this, viewModelFactory).get(TasksListViewModel::class.java)

        // 3. Подписываемся на LiveData для получения списка задач
        taskListViewModel.getAllTasks().observe(viewLifecycleOwner, Observer<List<TaskEntity>> {
            updateResults(it)
        })
    }

    private fun updatePlaceholder() {
        if (taskAdapter.isEmpty()) {
            placeholderContainer.visibility = View.VISIBLE
        } else {
            placeholderContainer.visibility = View.GONE
        }
    }

    private fun updateResults(tasks: List<TaskEntity>) {
        taskAdapter.setTasks(tasks)
        recyclerView.apply {
            adapter = taskAdapter
        }
        updatePlaceholder()
    }
}