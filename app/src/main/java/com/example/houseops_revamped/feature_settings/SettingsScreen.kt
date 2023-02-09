package com.example.houseops_revamped.feature_settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.houseops_revamped.feature_settings.presentation.components.SettingsAppBar
import com.example.houseops_revamped.feature_settings.presentation.components.about_section.AboutSection
import com.example.houseops_revamped.feature_settings.presentation.components.danger_section.DangerSection
import com.example.houseops_revamped.feature_settings.presentation.components.personalization_section.PersonalizationSection
import com.example.houseops_revamped.feature_settings.presentation.components.themes_section.ThemesSection
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navHostController: NavHostController
) {
    val settingsViewModel = SettingsViewModel()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            SettingsAppBar(
                onBackPressed = {}
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                //  Themes section
                ThemesSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(8.dp),
                    settingsViewModel = settingsViewModel,
                    context = context
                )

                //  PersonalizationSection
                PersonalizationSection(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(8.dp),
                    settingsViewModel = settingsViewModel
                )

                //  About Section
                AboutSection(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(8.dp),
                    settingsViewModel = settingsViewModel
                )

                //  Danger Section
                DangerSection(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(8.dp),
                    settingsViewModel = settingsViewModel
                )

            }

        }
    }
}











