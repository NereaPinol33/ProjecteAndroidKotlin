package com.example.taskapp.ui.theme.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.data.Task
import com.example.taskapp.data.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = TaskDatabase.getDatabase(application).taskDao()
    val allTasks: Flow<List<Task>> = dao.getAllTasks()
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                kotlinx.coroutines.delay(1000) // Simulación de 1 segundo de carga
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun insert(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(task)
        }
    }
}