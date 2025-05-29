package com.example.warmanearmory.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Armory : Screen("armory", "Armory", Icons.Default.Shield)
    object Details : Screen("details", "Details", Icons.Default.Info)
}