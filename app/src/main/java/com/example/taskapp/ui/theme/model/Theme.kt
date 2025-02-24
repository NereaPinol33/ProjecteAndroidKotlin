package com.example.taskapp.ui.theme.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF48FB1),
    secondary = Color(0xFFF06292),
    tertiary = Color(0xFFD81B60),
    background = Color(0xFFF1F1F1),
    surface = Color(0xFFF1F1F1),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFF48FB1),
    secondary = Color(0xFFF06292),
    tertiary = Color(0xFFD81B60),
    background = Color(0xFFC2185B),
    surface = Color(0xFFC2185B),
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