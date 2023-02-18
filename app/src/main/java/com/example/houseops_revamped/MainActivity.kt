package com.example.houseops_revamped

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.feature_settings.data.datastore.ThemePreference
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel
import com.example.houseops_revamped.feature_main_screen.presentation.MainScreen
import com.example.houseops_revamped.ui.theme.HouseOps_RevampedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {

            val context = LocalContext.current
            val settingsViewModel = SettingsViewModel(ThemePreference(context))

            HouseOps_RevampedTheme(
                darkTheme = when (settingsViewModel.themeFlow.collectAsState(initial = "").value) {
                    SettingsConstants.themeOptions[0] -> {
                        true
                    }
                    SettingsConstants.themeOptions[1] -> {
                        false
                    }
                    SettingsConstants.themeOptions[2] -> {
                        isSystemInDarkTheme()
                    }
                    else -> {
                        isSystemInDarkTheme()
                    }
                }
            ) {
                MainScreen(rememberNavController())
            }
        }
    }
}