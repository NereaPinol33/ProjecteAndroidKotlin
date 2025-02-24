package com.example.taskapp.ui.theme.fragments

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.taskapp.R

@Composable
fun AboutDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("About", color = MaterialTheme.colorScheme.onSurface) },
        text = { Text(stringResource(R.string.about_text), color = MaterialTheme.colorScheme.onSurface) },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary) // pink_500
            ) {
                Text("Close", color = MaterialTheme.colorScheme.onSecondary) // Texto blanco
            }
        },
        containerColor = MaterialTheme.colorScheme.background // pink_50
    )
}