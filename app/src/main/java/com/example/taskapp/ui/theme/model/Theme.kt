package com.example.taskapp.ui.theme.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFEFAC8C),
    secondary = Color(0xFFEE8453),
    tertiary = Color(0xFFD55217),
    background = Color(0xFFF1F1F1),
    surface = Color(0xFFE5E4E4),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFEFAC8C),
    secondary = Color(0xFFEE8453),
    tertiary = Color(0xFFD55217),
    background = Color(0xFFF1F1F1),
    surface = Color(0xFFE5E4E4),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun TaskAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}