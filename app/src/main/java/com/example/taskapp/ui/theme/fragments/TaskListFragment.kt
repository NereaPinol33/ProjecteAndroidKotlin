package com.example.taskapp.ui.theme.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    onAddTask: () -> Unit,
    onEditTask: (Task) -> Unit,
    navController: NavHostController,
    viewModel: TaskViewModel = viewModel()
) {
    val tasks by viewModel.allTasks.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()
    var showAboutDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTask,
                containerColor = MaterialTheme.colorScheme.secondary // pink_500 para el FAB
            ) {
                Text("+", color = MaterialTheme.colorScheme.onSecondary) // Texto blanco sobre pink_500
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Task List", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {},
                actions = {
                    Button(
                        onClick = { showAboutDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary) // pink_500
                    ) {
                        Text("About", color = MaterialTheme.colorScheme.onSecondary) // Texto blanco
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
                    painter = painterResource(id = R.drawable.petly_loading),
                    contentDescription = "Loading Notebook",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(16.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background) // pink_50 como fondo
            ) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onClick = { onEditTask(task) },
                        onDelete = { viewModel.delete(task) }
                    )
                }
            }
        }
    }

    if (showAboutDialog) {
        AboutDialog(onDismiss = { showAboutDialog = false })
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    task.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    task.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Delete", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}