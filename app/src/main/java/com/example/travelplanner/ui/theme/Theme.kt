package com.example.travelplanner.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = OceanBlue,
    secondary = FreshTeal,
    tertiary = WarmCoral,
    background = Cloud,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Ink,
    onTertiary = Color.White,
    onBackground = Ink,
    onSurface = Ink
)

private val DarkColorScheme = darkColorScheme(
    primary = FreshTeal,
    secondary = SoftMint,
    tertiary = WarmCoral,
    background = Ink,
    surface = Color(0xFF21303A)
)

@Composable
fun TravelPlannerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
