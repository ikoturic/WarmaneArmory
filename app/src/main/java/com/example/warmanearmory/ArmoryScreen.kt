package com.example.warmanearmory.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.warmanearmory.Character3DViewer
import com.example.warmanearmory.ItemWithIcon
import com.example.warmanearmory.viewmodel.CharacterViewModel

@Composable
fun ArmoryScreen(viewModel: CharacterViewModel) {
    val character = viewModel.character.collectAsState().value

    Column(Modifier.padding(16.dp)) {
        character?.let {
            // Zamijeniti WebView sa url-om na profil sa custom 3D prikazom:
            Character3DViewer(
                name = it.name,
                realm = it.realm,
                race = (it.race as? String)?.toIntOrNull() ?: (it.race as? Int) ?: 8,
                gender = (it.gender as? String)?.toIntOrNull() ?: (it.gender as? Int) ?: 0,


                items = it.equipment.map { eq -> eq.item.toIntOrNull() ?: 0 }.filter { it != 0 }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Oprema:", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(it.equipment) { item ->
                    ItemWithIcon(itemId = item.item, itemName = item.name)
                }
            }
        } ?: Text("Nema podataka.")
    }
}


@Composable
fun WarmaneCharacterProfile(name: String, realm: String) {
    val url = "http://armory.warmane.com/character/$name/$realm/profile"
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .height(1000.dp)) // prilagodi visinu po potrebi
}