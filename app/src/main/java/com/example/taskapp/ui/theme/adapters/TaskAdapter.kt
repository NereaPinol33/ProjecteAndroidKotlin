package com.example.taskapp.ui.theme.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.data.Task
import com.example.taskapp.databinding.ItemTaskBinding

class TaskAdapter(
    private val onItemClick: (Task) -> Unit,
    private val onEditClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDescription.text = "${task.description} (${task.color}, ${task.personality})"

            // Asignar imagen según el color del gato
            when (task.color.lowercase()) {
                "negro" -> binding.catImage.setImageResource(R.drawable.black_cat)
                "blanco" -> binding.catImage.setImageResource(R.drawable.white_cat)
                "atigrado" -> binding.catImage.setImageResource(R.drawable.orange_cat)
                else -> binding.catImage.setImageResource(R.drawable.default_cat)
            }

            // Configurar clics
            binding.root.setOnClickListener { onItemClick(task) }
            binding.editButton.setOnClickListener { onEditClick(task) }
            binding.deleteButton.setOnClickListener { onDeleteClick(task) }
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
}