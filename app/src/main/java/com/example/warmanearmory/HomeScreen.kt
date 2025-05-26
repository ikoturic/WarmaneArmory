package com.example.warmanearmory.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.warmanearmory.model.CharacterData
import com.example.warmanearmory.network.RetrofitInstance
import com.example.warmanearmory.navigation.Screen
import com.example.warmanearmory.viewmodel.CharacterViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, viewModel: CharacterViewModel) {
    var name by remember { mutableStateOf("") }
    var realm by remember { mutableStateOf("Icecrown") }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Ime karaktera") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = realm,
            onValueChange = { realm = it },
            label = { Text("Realm") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                scope.launch {
                    loading = true
                    try {
                        val character = RetrofitInstance.api.getCharacter(name, realm)
                        viewModel.setCharacter(character)
                        navController.navigate(Screen.Armory.route)
                    } catch (e: Exception) {
                        // Error handling
                    }
                    loading = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (loading) "Učitavanje..." else "Dohvati")
        }
    }
}
