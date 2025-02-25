package com.example.taskapp.ui.theme.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    var color by remember { mutableStateOf("") }
    var personality by remember { mutableStateOf("") }
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
                    color = task.color
                    personality = task.personality
                } ?: run {
                    title = ""
                    description = ""
                    color = ""
                    personality = ""
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
                title = { Text(if (taskId == null) "Add Cat" else "Edit Cat", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Cat List",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary // cat_gray
                )
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.petly_loading),
                    contentDescription = "Loading Cat",
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background) // cat_light_gray como fondo
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Cat Name", color = MaterialTheme.colorScheme.onSurface) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary, // cat_orange
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary, // cat_gray
                        focusedLabelColor = MaterialTheme.colorScheme.secondary, // cat_orange
                        cursorColor = MaterialTheme.colorScheme.secondary // cat_orange
                    )
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Age or Description", color = MaterialTheme.colorScheme.onSurface) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary, // cat_orange
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary, // cat_gray
                        focusedLabelColor = MaterialTheme.colorScheme.secondary, // cat_orange
                        cursorColor = MaterialTheme.colorScheme.secondary // cat_orange
                    )
                )
                OutlinedTextField(
                    value = color,
                    onValueChange = { color = it },
                    label = { Text("Color (e.g., Negro, Blanco)", color = MaterialTheme.colorScheme.onSurface) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary, // cat_orange
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary, // cat_gray
                        focusedLabelColor = MaterialTheme.colorScheme.secondary, // cat_orange
                        cursorColor = MaterialTheme.colorScheme.secondary // cat_orange
                    )
                )
                OutlinedTextField(
                    value = personality,
                    onValueChange = { personality = it },
                    label = { Text("Personality (e.g., Juguetón, Tímido)", color = MaterialTheme.colorScheme.onSurface) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary, // cat_orange
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary, // cat_gray
                        focusedLabelColor = MaterialTheme.colorScheme.secondary, // cat_orange
                        cursorColor = MaterialTheme.colorScheme.secondary // cat_orange
                    )
                )
                Button(
                    onClick = {
                        if (title.isNotEmpty()) {
                            val task = if (taskId == null) {
                                Task(title = title, description = description, color = color, personality = personality)
                            } else {
                                Task(id = taskId, title = title, description = description, color = color, personality = personality)
                            }
                            if (taskId == null) viewModel.insert(task) else viewModel.update(task)
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary // cat_orange
                    )
                ) {
                    Text("Save", color = MaterialTheme.colorScheme.onSecondary) // Texto blanco
                }
            }
        }
    }
}