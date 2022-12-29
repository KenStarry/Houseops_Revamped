package com.example.houseops_revamped.ui.theme

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = BlueAccent,
    secondary = PinkAccent,
    tertiary = BlueAccentLight,
    onPrimary = DarkBackground,
    onSecondary = DarkBackgroundAlt,
    onSecondaryContainer = TextWhite900
)

private val LightColorScheme = lightColorScheme(
    primary = BlueAccent,
    secondary = PinkAccent,
    tertiary = BlueAccentLight,
    onPrimary = LightBackgroundAlt,
    onSecondary = LightBackground,
    onSecondaryContainer = TextBlack900

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun HouseOps_RevampedTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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

    //using accompanyst library
    val systemUiController = rememberSystemUiController()
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {

            //  changing status bar background and foreground Icons
            systemUiController.setStatusBarColor(
                color = colorScheme.onPrimary,
                darkIcons = !darkTheme
            )

            //  changing naviagtion background and foreground
            systemUiController.setNavigationBarColor(
                color = colorScheme.onPrimary,
                darkIcons = !darkTheme
            )


//            val window = (view.context as Activity).window
//
//            //  change status bar and navigationbar background colors
//            window.statusBarColor = colorScheme.onPrimary.toArgb()
//            window.navigationBarColor = colorScheme.onPrimary.toArgb()
//
//            //  change status bar and navigationbar text color
//            WindowCompat.getInsetsController(window, view)
//                .isAppearanceLightStatusBars = darkTheme
//            WindowCompat.getInsetsController(window, view)
//                .isAppearanceLightNavigationBars = darkTheme
//
//            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}