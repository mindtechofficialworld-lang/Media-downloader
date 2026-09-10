package com.mindtech.mediadownloader.ui.screens.home

import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mindtech.mediadownloader.R

@Composable
fun HomeScreen(sharedUrl: String? = null) {
    val context = LocalContext.current
    var urlInput by remember { mutableStateOf(sharedUrl ?: "") }
    var showAnalyzeResult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .semantics { contentDescription = "Media Downloader" }
        )

        Text(
            text = stringResource(R.string.home_url_label),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .semantics { contentDescription = "Video or Audio URL input field" }
        )

        OutlinedTextField(
            value = urlInput,
            onValueChange = { urlInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .semantics { contentDescription = "URL input field for video or audio download" },
            placeholder = {
                Text(stringResource(R.string.home_url_hint))
            },
            singleLine = true
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    val clipboardManager =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData = clipboardManager.primaryClip
                    if (clipData != null && clipData.itemCount > 0) {
                        urlInput = clipData.getItemAt(0).text.toString()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .semantics { contentDescription = "Paste URL from clipboard" }
            ) {
                Text(stringResource(R.string.home_paste_button))
            }

            Button(
                onClick = { urlInput = "" },
                modifier = Modifier
                    .weight(1f)
                    .semantics { contentDescription = "Clear URL input field" }
            ) {
                Text(stringResource(R.string.home_clear_button))
            }
        }

        Button(
            onClick = {
                if (urlInput.isNotEmpty()) {
                    showAnalyzeResult = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .semantics { contentDescription = "Analyze media URL to get available qualities and formats" }
        ) {
            Text(stringResource(R.string.home_analyze_button))
        }

        if (showAnalyzeResult && urlInput.isNotEmpty()) {
            Divider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Media Analysis Result",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "URL: $urlInput",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "(Analysis functionality will be implemented in next stages)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (!showAnalyzeResult) {
            Divider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = stringResource(R.string.home_quick_actions),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .semantics { contentDescription = "Quick actions for accessing downloads" }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .weight(1f)
                        .semantics { contentDescription = "View videos" }
                ) {
                    Text(stringResource(R.string.home_action_videos))
                }

                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .weight(1f)
                        .semantics { contentDescription = "View audio" }
                ) {
                    Text(stringResource(R.string.home_action_audio))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .weight(1f)
                        .semantics { contentDescription = "View current downloads" }
                ) {
                    Text(stringResource(R.string.home_action_downloading))
                }

                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .weight(1f)
                        .semantics { contentDescription = "Search downloads" }
                ) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            }
        }
    }
}
