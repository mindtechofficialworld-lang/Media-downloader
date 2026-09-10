package com.mindtech.mediadownloader

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mindtech.mediadownloader.ui.navigation.MainNavigation
import com.mindtech.mediadownloader.ui.theme.MediaDownloaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var sharedUrl: String? = null
        if (intent?.action == Intent.ACTION_SEND) {
            sharedUrl = intent.getStringExtra(Intent.EXTRA_TEXT)
        }

        setContent {
            MediaDownloaderTheme {
                Surface(
                    modifier = androidx.compose.foundation.layout.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(sharedUrl = sharedUrl)
                }
            }
        }
    }
}
