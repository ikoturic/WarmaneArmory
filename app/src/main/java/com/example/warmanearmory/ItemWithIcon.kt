package com.example.warmanearmory

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

// Dodano:
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun ItemWithIcon(itemId: String, itemName: String) {
    var iconName by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(itemId) {
        scope.launch {
            try {
                val response = RetrofitWowheadInstance.api.getItemTooltip(itemId)
                if (response.isSuccessful) {
                    val body = response.body()?.string()
                    val regex = Regex("""icon: '([^']+)'""")
                    val match = regex.find(body ?: "")
                    iconName = match?.groupValues?.get(1)
                }
            } catch (e: Exception) {
                // handle error
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        if (iconName != null) {
            AsyncImage(
                model = "https://wow.zamimg.com/images/wow/icons/large/${iconName}.jpg",
                contentDescription = itemName,
                modifier = Modifier.size(40.dp)
            )
        } else {
            Box(modifier = Modifier.size(40.dp)) // placeholder
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(itemName)
    }
}