package com.example.houseops_revamped.feature_settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.components.CustomAlertDialog
import com.example.houseops_revamped.core.presentation.model.AccentColor
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_settings.data.datastore.AccentPreference
import com.example.houseops_revamped.feature_settings.data.datastore.ThemePreference
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.components.SettingsAppBar
import com.example.houseops_revamped.feature_settings.presentation.components.alert_dialogs.AccentDialog
import com.example.houseops_revamped.feature_settings.presentation.components.miscellaneous_section.MiscellaneousSection
import com.example.houseops_revamped.feature_settings.presentation.components.danger_section.DangerSection
import com.example.houseops_revamped.feature_settings.presentation.components.personalization_section.PersonalizationSection
import com.example.houseops_revamped.feature_settings.presentation.components.themes_section.ThemesSection
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val coreVM: CoreViewModel = hiltViewModel()

    val primaryColor = Color(
        coreVM.primaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].darkColor
        ).value ?: Constants.accentColors[0].darkColor
    )

    val tertiaryColor = Color(
        coreVM.tertiaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].lightColor
        ).value ?: Constants.accentColors[0].lightColor
    )

    val settingsViewModel = SettingsViewModel(
        themePreference = ThemePreference(context),
        accentPreference = AccentPreference(context)
    )

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            SettingsAppBar(
                onBackPressed = {},
                settingsViewModel = settingsViewModel
            )
        }
    ) { contentPadding ->

        //  accent alert dialog
        AnimatedVisibility(visible = settingsViewModel.isAccentDialogVisible.value) {
            AccentDialog(
                coreVM = coreVM,
                settingsViewModel = settingsViewModel,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
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
                    context = context,
                    settingsViewModel = settingsViewModel,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )

                //  PersonalizationSection
                PersonalizationSection(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(8.dp),
                    settingsViewModel = settingsViewModel,
                    coreViewModel = coreVM,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onChangeAccentClicked = {

                        // open the alert dialog
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleAlertDialog(
                                alertType = SettingsConstants.ACCENT_ALERT_DIALOG,
                                isVisible = true
                            )
                        )
                    }
                )

                //  About Section
                MiscellaneousSection(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(8.dp),
                    settingsViewModel = settingsViewModel,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )

                //  Danger Section
                DangerSection(
                    context = context,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .padding(8.dp),
                    settingsViewModel = settingsViewModel,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )

            }

        }
    }
}











