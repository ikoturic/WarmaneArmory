package com.example.warmanearmory

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

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