package ru.sysdyn.tasktraker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.sysdyn.tasktraker.data.database.TaskCategoryConverter

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "isCompleted") val isCompleted: Boolean,

    @ColumnInfo(name = "category")
    @TypeConverters(TaskCategoryConverter::class)
    val category: TaskCategory
)