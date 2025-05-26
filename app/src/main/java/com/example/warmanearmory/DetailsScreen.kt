package com.example.warmanearmory.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.warmanearmory.viewmodel.CharacterViewModel

@Composable
fun DetailsScreen(viewModel: CharacterViewModel) {
    val character = viewModel.character.collectAsState().value

    Column(Modifier.padding(16.dp)) {
        character?.let {
            Text("Achievementi: ${it.achievementpoints}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Profesije:")
            it.professions.forEach { prof ->
                Text("• ${prof.name} (${prof.skill})")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Talenti:")
            it.talents.forEach { talent ->
                Text("• ${talent.tree} - ${talent.points.joinToString("/")}")
            }
        } ?: Text("Nema podataka.")
    }
}
