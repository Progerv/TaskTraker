package ru.sysdyn.tasktraker

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.ChipGroup
import ru.sysdyn.tasktraker.data.model.TaskCategory
import ru.sysdyn.tasktraker.data.model.TaskEntity

class CreateTaskDialog(
    context: Context,
    private val onMerge: (task: TaskEntity) -> Unit
) : BottomSheetDialog(context, R.style.TransparentBottomSheet) {

    override fun onCreate(savedInstanceState: Bundle?) {
        val contentView = View.inflate(context, R.layout.dialog_create_task, null)
        setContentView(contentView)

        val saveButton = contentView.findViewById<LinearLayout>(R.id.save_button)
        val taskNameEditText = contentView.findViewById<EditText>(R.id.task_name_edit_text)
        val chipGroup = contentView.findViewById<ChipGroup>(R.id.chip_group)

        saveButton.setOnClickListener {
            val newTask = TaskEntity(
                title = taskNameEditText.text.toString(),
                isCompleted = false,
                category = getCategoryFromChipId(chipGroup.checkedChipId)
            )

            onMerge(newTask)
            dismiss()
        }

        super.onCreate(savedInstanceState)
    }

    private fun getCategoryFromChipId(chipId: Int): TaskCategory {
        return when (chipId) {
            R.id.shopping_ship -> TaskCategory.SHOP
            R.id.work_chip -> TaskCategory.WORK
            R.id.fitness_chip -> TaskCategory.FITNESS
            R.id.study_chip -> TaskCategory.STUDY
            R.id.personal_chip -> TaskCategory.PERSONAL
            else -> TaskCategory.PERSONAL
        }
    }

    companion object {
        fun newInstance(
            context: Context,
            onMerge: (task: TaskEntity) -> Unit
        ) {
            val dialog = CreateTaskDialog(context, onMerge)
            dialog.show()
        }
    }
}