package com.example.taskapp.ui.theme.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskapp.data.Task
import com.example.taskapp.ui.theme.viewmodel.TaskViewModel
import androidx.navigation.NavHostController
import com.example.taskapp.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    taskId: Int?,
    onNavigateBack: () -> Unit,
    navController: NavHostController,
    viewModel: TaskViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    val tasks by viewModel.allTasks.collectAsState(initial = emptyList())

    // Cargar los datos de la tarea si estamos editando
    LaunchedEffect(taskId) {
        isLoading = true
        try {
            delay(100) // Pequeño retraso para simular carga (opcional, puedes eliminarlo)
            taskId?.let { id ->
                tasks.find { it.id == id }?.let { task ->
                    title = task.title
                    description = task.description
                } ?: run {
                    title = ""
                    description = ""
                }
            }
            isLoading = false
        } catch (e: Exception) {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (taskId == null) "Add Task" else "Edit Task", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Task List",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary // pink_300
                )
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.notebook_loading),
                    contentDescription = "Loading Notebook",
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background) // pink_50 como fondo
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title", color = MaterialTheme.colorScheme.onSurface) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary, // pink_500
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary, // pink_700
                        focusedLabelColor = MaterialTheme.colorScheme.secondary, // pink_500
                        cursorColor = MaterialTheme.colorScheme.secondary // pink_500
                    )
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description", color = MaterialTheme.colorScheme.onSurface) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary, // pink_500
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary, // pink_700
                        focusedLabelColor = MaterialTheme.colorScheme.secondary, // pink_500
                        cursorColor = MaterialTheme.colorScheme.secondary // pink_500
                    )
                )
                Button(
                    onClick = {
                        if (title.isNotEmpty()) {
                            val task = if (taskId == null) {
                                Task(title = title, description = description)
                            } else {
                                Task(id = taskId, title = title, description = description)
                            }
                            if (taskId == null) viewModel.insert(task) else viewModel.update(task)
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary // pink_500
                    )
                ) {
                    Text("Save", color = MaterialTheme.colorScheme.onSecondary) // Texto blanco
                }
            }
        }
    }
}