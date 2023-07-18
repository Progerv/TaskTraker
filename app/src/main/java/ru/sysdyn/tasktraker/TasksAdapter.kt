package ru.sysdyn.tasktraker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.sysdyn.tasktraker.data.model.TaskCategory
import ru.sysdyn.tasktraker.data.model.TaskEntity

class TasksAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    private var tasks: List<TaskEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val quote = tasks[position]
        holder.bind(quote)
    }

    override fun getItemCount(): Int = tasks.size

    fun setTasks(t: List<TaskEntity>) {
        this.tasks = t
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return tasks.isEmpty()
    }
}

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.task_name)
    private val checkBox: CheckBox = view.findViewById(R.id.status_checkbox)
    private val categoryMarker: View = view.findViewById(R.id.category_marker)

    fun bind(currentTask: TaskEntity) {
        title.text = currentTask.title
        checkBox.isChecked = currentTask.isCompleted
        categoryMarker.setBackgroundColor(
            ContextCompat.getColor(
                title.context,
                resolveColor(currentTask.category)
            )
        )
    }

    @ColorRes
    fun resolveColor(category: TaskCategory): Int {
        return when (category) {
            TaskCategory.WORK -> R.color.work_color
            TaskCategory.PERSONAL -> R.color.personal_color
            TaskCategory.SHOP -> R.color.shop_color
            TaskCategory.FITNESS -> R.color.fitness_color
            TaskCategory.STUDY -> R.color.study_color
        }
    }
}