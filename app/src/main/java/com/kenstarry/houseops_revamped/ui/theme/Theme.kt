    package com.kenstarry.houseops_revamped.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.ui.custom.LocalSpacing
import com.kenstarry.houseops_revamped.ui.custom.Spacing

    private val DarkColorScheme = darkColorScheme(
    primary = Constants.primaryCol.value,
    secondary = PinkAccent,
    tertiary = Constants.tertiaryCol.value,
    onPrimary = DarkBackground,
    onSecondary = BlackBackgroundAlt,
    onSecondaryContainer = TextWhite900
)

private val LightColorScheme = lightColorScheme(
    primary = Constants.primaryCol.value,
    secondary = PinkAccent,
    tertiary = Constants.tertiaryCol.value,
    onPrimary = WhiteBackground,
    onSecondary = GreyBackground,
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
        }
    }

    //  composition local provider helps us define custom theming
    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}