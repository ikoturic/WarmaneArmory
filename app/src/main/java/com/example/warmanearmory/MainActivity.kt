package com.example.warmanearmory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.warmanearmory.model.CharacterData
import com.example.warmanearmory.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarmaneApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarmaneApp() {
    var name by remember { mutableStateOf("") }
    var realm by remember { mutableStateOf("Icecrown") }
    var character by remember { mutableStateOf<CharacterData?>(null) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Warmane Armory") })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(16.dp).padding(padding)) {
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
                            character = RetrofitInstance.api.getCharacter(name, realm)
                        } catch (e: Exception) {
                            character = null
                        }
                        loading = false
                    }
                },
                enabled = !loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (loading) "Učitavanje..." else "Dohvati")
            }

            Spacer(modifier = Modifier.height(16.dp))

            character?.let { data ->
                Text("Ime: ${data.name}", style = MaterialTheme.typography.titleLarge)
                Text("Level: ${data.level}")
                Text("Klasa: ${data.className}")
                Text("Rasa: ${data.race}")
                Text("Spol: ${data.gender}")
                Text("Frakcija: ${data.faction}")
                Text("Guild: ${data.guild ?: "Nema"}")
                Text("HK: ${data.honorablekills}")
                Text("Achievementi: ${data.achievementpoints}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Profesije:")
                data.professions.forEach {
                    Text("• ${it.name} (${it.skill})")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Oprema:")
                LazyColumn {
                    items(data.equipment) { item ->
                        Text("• ${item.name}")
                    }
                }
            }
        }
    }
}
