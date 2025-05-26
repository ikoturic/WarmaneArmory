package com.example.warmanearmory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.warmanearmory.navigation.Screen
import com.example.warmanearmory.screens.*
import com.example.warmanearmory.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: CharacterViewModel = viewModel()
            val screens = listOf(Screen.Home, Screen.Armory, Screen.Details)

            Scaffold(
                bottomBar = {
                    NavigationBar {
                        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                        screens.forEach { screen ->
                            NavigationBarItem(
                                selected = currentRoute == screen.route,
                                onClick = {
                                    if (currentRoute != screen.route) {
                                        navController.navigate(screen.route)
                                    }
                                },
                                icon = { Icon(screen.icon, contentDescription = screen.title) },
                                label = { Text(screen.title) }
                            )
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Home.route) { HomeScreen(navController, viewModel) }
                    composable(Screen.Armory.route) { ArmoryScreen(viewModel) }
                    composable(Screen.Details.route) { DetailsScreen(viewModel) }
                }
            }
        }
    }
}
