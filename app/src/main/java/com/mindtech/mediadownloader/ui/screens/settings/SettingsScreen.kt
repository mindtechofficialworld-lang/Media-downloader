package com.mindtech.mediadownloader.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mindtech.mediadownloader.R

@Composable
fun SettingsScreen() {
    var lightTheme by remember { mutableStateOf(false) }
    var clipboardDetection by remember { mutableStateOf(true) }
    var downloadNotifications by remember { mutableStateOf(true) }
    var playbackNotifications by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.nav_settings),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .semantics { contentDescription = "Settings" }
        )

        SettingsSection(
            title = stringResource(R.string.settings_appearance),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            SettingItem(
                label = stringResource(R.string.settings_theme_light),
                isChecked = lightTheme,
                onCheckedChange = { lightTheme = it }
            )
        }

        SettingsSection(
            title = stringResource(R.string.settings_download),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            SettingItem(
                label = stringResource(R.string.settings_clipboard_detection),
                isChecked = clipboardDetection,
                onCheckedChange = { clipboardDetection = it }
            )
        }

        SettingsSection(
            title = stringResource(R.string.settings_notifications),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            SettingItem(
                label = stringResource(R.string.settings_download_started),
                isChecked = downloadNotifications,
                onCheckedChange = { downloadNotifications = it }
            )
            SettingItem(
                label = stringResource(R.string.settings_playback_notification),
                isChecked = playbackNotifications,
                onCheckedChange = { playbackNotifications = it }
            )
        }

        SettingsSection(
            title = stringResource(R.string.settings_storage),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Storage management features coming soon",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        SettingsSection(
            title = stringResource(R.string.settings_app),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "App info and resources",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .semantics { contentDescription = title }
        )
        content()
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Composable
private fun SettingItem(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .semantics { contentDescription = label },
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.semantics { contentDescription = "$label toggle" }
        )
    }
}
