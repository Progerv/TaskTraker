package ru.sysdyn.tasktraker.data.database

import androidx.room.TypeConverter
import ru.sysdyn.tasktraker.data.model.TaskCategory

class TaskCategoryConverter {
    @TypeConverter
    fun fromTaskCategory(task: TaskCategory): String {
        return task.name
    }

    @TypeConverter
    fun toTypeCategory(data: String): TaskCategory {
        return when (data) {
            "PERSONAL" -> TaskCategory.PERSONAL
            "WORK" -> TaskCategory.WORK
            "SHOP" -> TaskCategory.SHOP
            "FITNESS" -> TaskCategory.FITNESS
            "STUDY" -> TaskCategory.STUDY
            else -> throw UnsupportedOperationException("No such type $data")
        }
    }
}