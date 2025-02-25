package com.example.taskapp.ui.theme.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
                containerColor = MaterialTheme.colorScheme.secondary // cat_orange para el FAB
            ) {
                Text("+", color = MaterialTheme.colorScheme.onSecondary) // Texto blanco
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Cat List", color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {},
                actions = {
                    IconButton(
                        onClick = { showAboutDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit, // Usamos ícono de edición para "About"
                            contentDescription = "About",
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
                        .size(150.dp)
                        .padding(16.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background) // cat_light_gray como fondo
            ) {
                items(tasks) { task ->
                    CatItem(
                        task = task,
                        onClick = { onEditTask(task) },
                        onEdit = { onEditTask(task) },
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
fun CatItem(task: Task, onClick: () -> Unit, onEdit: () -> Unit, onDelete: () -> Unit) {
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
                    "${task.description} (${task.color}, ${task.personality})",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Row {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Cat",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Cat",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun AboutDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("About") },
        text = { Text("Created by Nerea Piñol\nCat Management App\nVersion 1.0\nFebruary 2025") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}