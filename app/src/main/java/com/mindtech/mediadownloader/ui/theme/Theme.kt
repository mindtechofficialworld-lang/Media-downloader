package com.mindtech.mediadownloader.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val PrimaryPurple = Color(0xFF9C27B0)
private val PrimaryLightPurple = Color(0xFFBA68C8)
private val PrimaryDarkPurple = Color(0xFF7B1FA2)
private val SecondaryOrange = Color(0xFFFF5722)
private val SecondaryLightOrange = Color(0xFFFF8A65)
private val SecondaryDarkOrange = Color(0xFFE64A19)
private val TertiaryBlue = Color(0xFF2196F3)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryPurple,
    onPrimary = Color.White,
    primaryContainer = PrimaryLightPurple,
    onPrimaryContainer = Color.White,
    secondary = SecondaryOrange,
    onSecondary = Color.White,
    secondaryContainer = SecondaryLightOrange,
    onSecondaryContainer = Color.White,
    tertiary = TertiaryBlue,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE1F5FE),
    onTertiaryContainer = TertiaryBlue,
    error = Color(0xFFF44336),
    onError = Color.White,
    errorContainer = Color(0xFFFFEBEE),
    onErrorContainer = Color(0xFFF44336),
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454E),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC7D0),
    scrim = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryLightPurple,
    onPrimary = PrimaryDarkPurple,
    primaryContainer = PrimaryDarkPurple,
    onPrimaryContainer = PrimaryLightPurple,
    secondary = SecondaryLightOrange,
    onSecondary = SecondaryDarkOrange,
    secondaryContainer = SecondaryDarkOrange,
    onSecondaryContainer = SecondaryLightOrange,
    tertiary = Color(0xFFB3E5FC),
    onTertiary = Color(0xFF01579B),
    tertiaryContainer = Color(0xFF01579B),
    onTertiaryContainer = Color(0xFFB3E5FC),
    error = Color(0xFFEF5350),
    onError = Color(0xFF1A0000),
    errorContainer = Color(0xFFC62828),
    onErrorContainer = Color(0xFFFFEBEE),
    background = Color(0xFF121212),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF121212),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454E),
    onSurfaceVariant = Color(0xFFCAC7D0),
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454E),
    scrim = Color.Black
)

@Composable
fun MediaDownloaderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
