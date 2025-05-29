// Character3DViewer.kt
package com.example.warmanearmory

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp

@Composable
fun Character3DViewer(name: String, realm: String, race: Int = 8, gender: Int = 0, items: List<Int> = listOf(18803,16908,16909,16910,16911)) {
    val urlParams = "race=$race&gender=$gender&items=${items.joinToString(",")}"

    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl("file:///android_asset/modelviewer.html?$urlParams")
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .height(600.dp))
}