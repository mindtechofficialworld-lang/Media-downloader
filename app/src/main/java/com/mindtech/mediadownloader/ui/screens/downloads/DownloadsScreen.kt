package com.mindtech.mediadownloader.ui.screens.downloads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mindtech.mediadownloader.R

enum class DownloadsTab(val labelRes: Int) {
    VIDEOS(R.string.downloads_tab_videos),
    AUDIO(R.string.downloads_tab_audio),
    DOWNLOADING(R.string.downloads_tab_downloading)
}

@Composable
fun DownloadsScreen() {
    var selectedTab by remember { mutableStateOf(DownloadsTab.VIDEOS) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTab.ordinal,
            modifier = Modifier.semantics { contentDescription = "Downloads tabs navigation" }
        ) {
            DownloadsTab.values().forEach { tab ->
                Tab(
                    text = {
                        Text(
                            text = stringResource(tab.labelRes),
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    modifier = Modifier.semantics { contentDescription = stringResource(tab.labelRes) }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when (selectedTab) {
                DownloadsTab.VIDEOS -> VideoLibraryTab()
                DownloadsTab.AUDIO -> AudioLibraryTab()
                DownloadsTab.DOWNLOADING -> DownloadingTab()
            }
        }
    }
}

@Composable
private fun VideoLibraryTab() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.downloads_empty),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
private fun AudioLibraryTab() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.downloads_empty),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
private fun DownloadingTab() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.downloads_empty),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}
