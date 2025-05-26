package com.example.warmanearmory.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.warmanearmory.viewmodel.CharacterViewModel

@Composable
fun ArmoryScreen(viewModel: CharacterViewModel) {
    val character = viewModel.character.collectAsState().value

    Column(Modifier.padding(16.dp)) {
        Text("Oprema:", style = MaterialTheme.typography.titleLarge)
        character?.equipment?.let {
            LazyColumn {
                items(it) { item ->
                    Text("• ${item.name}")
                }
            }
        } ?: Text("Nema podataka.")
    }
}
