package com.mindtech.mediadownloader.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mindtech.mediadownloader.R
import com.mindtech.mediadownloader.ui.screens.home.HomeScreen
import com.mindtech.mediadownloader.ui.screens.downloads.DownloadsScreen
import com.mindtech.mediadownloader.ui.screens.settings.SettingsScreen

enum class NavigationItem(val label: Int, val icon: androidx.compose.material.icons.Icons) {
    HOME(R.string.nav_home, Icons.Default.Home),
    DOWNLOADS(R.string.nav_downloads, Icons.Default.Download),
    SETTINGS(R.string.nav_settings, Icons.Default.Settings)
}

@Composable
fun MainNavigation(sharedUrl: String? = null) {
    var selectedTab by remember { mutableStateOf(NavigationItem.HOME) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            when (selectedTab) {
                NavigationItem.HOME -> HomeScreen(sharedUrl = sharedUrl)
                NavigationItem.DOWNLOADS -> DownloadsScreen()
                NavigationItem.SETTINGS -> SettingsScreen()
            }
        }

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding()
        ) {
            NavigationItem.values().forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(item.label),
                            modifier = Modifier
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(item.label),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    selected = selectedTab == item,
                    onClick = { selectedTab = item },
                    modifier = Modifier
                )
            }
        }
    }
}
