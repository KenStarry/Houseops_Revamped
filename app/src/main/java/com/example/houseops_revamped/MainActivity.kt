package com.example.houseops_revamped

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.viewmodel.SettingsViewModel
import com.example.houseops_revamped.navigation.graphs.RootNavGraph
import com.example.houseops_revamped.ui.theme.HouseOps_RevampedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {

            val context = LocalContext.current
            val settingsViewModel: SettingsViewModel = hiltViewModel()

            HouseOps_RevampedTheme(
                darkTheme = when (settingsViewModel.themeFlow.collectAsState(initial = "").value) {
                    SettingsConstants.themeOptions[0].title -> {
                        true
                    }
                    SettingsConstants.themeOptions[1].title -> {
                        false
                    }
                    SettingsConstants.themeOptions[2].title -> {
                        isSystemInDarkTheme()
                    }
                    else -> {
                        isSystemInDarkTheme()
                    }
                }
            ) {
//                MainScreen(rememberNavController())

                val coreVM: CoreViewModel = hiltViewModel()
                val userType = coreVM.userTypeFlow.collectAsState(initial = null).value

                RootNavGraph(
                    navHostController = rememberNavController(),
                    isLoggedIn = coreVM.isUserLoggedIn(),
                    userType = userType
                )
            }
        }
    }
}