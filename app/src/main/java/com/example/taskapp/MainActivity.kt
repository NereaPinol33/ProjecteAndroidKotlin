package com.example.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskapp.ui.theme.fragments.TaskListScreen
import com.example.taskapp.ui.theme.fragments.AddEditTaskScreen
import com.example.taskapp.ui.theme.model.TaskAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskAppTheme {
                TaskApp()
            }
        }
    }
}

@Composable
fun TaskApp() {
    val navController = rememberNavController()
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        showSplash = false
        navController.navigate("task_list") {
            popUpTo("start_screen") { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = "start_screen") {
        composable("start_screen") {
            if (showSplash) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.petly_loading),
                        contentDescription = "Too Do List Logo",
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            } else {
                // Navegar automáticamente a task_list después del retraso
                TaskListScreen(
                    onAddTask = { navController.navigate("add_edit_task") },
                    onEditTask = { task -> navController.navigate("add_edit_task?taskId=${task.id}") },
                    navController = navController
                )
            }
        }
        composable("task_list") {
            TaskListScreen(
                onAddTask = { navController.navigate("add_edit_task") },
                onEditTask = { task -> navController.navigate("add_edit_task?taskId=${task.id}") },
                navController = navController
            )
        }
        composable("add_edit_task?taskId={taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            AddEditTaskScreen(
                taskId = taskId,
                onNavigateBack = { navController.popBackStack() },
                navController = navController
            )
        }
    }
}