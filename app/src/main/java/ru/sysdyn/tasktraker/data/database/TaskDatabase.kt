package ru.sysdyn.tasktraker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineDispatcher
import ru.sysdyn.tasktraker.data.model.TaskCategory
import ru.sysdyn.tasktraker.data.model.TaskEntity

const val DATABASE_VERSION_CODE = 1

@TypeConverters(TaskCategoryConverter::class)
@Database(entities = [TaskEntity::class], version = DATABASE_VERSION_CODE, exportSchema = true)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context, coroutineDispatcher: CoroutineDispatcher): TaskDatabase? {
            if (INSTANCE == null) {
                synchronized(TaskDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        TaskDatabase::class.java, "todo_database"
                    ).build()
                }
            }
            return INSTANCE
        }

        val PREPOPULATE_DATA = listOf(
            TaskEntity(
                1,
                "Прочитать книгу Рефакторинг",
                false,
                category = TaskCategory.STUDY
            ),
            TaskEntity(
                2,
                "Купить авиабилеты",
                false,
                category = TaskCategory.PERSONAL
            )
        )
    }
}