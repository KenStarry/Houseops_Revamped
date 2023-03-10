package com.example.houseops_revamped.feature_agent.feature_settings

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_tenant.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.components.SettingsAppBar
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.components.alert_dialogs.AccentDialog
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.components.danger_section.DangerSection
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.components.personalization_section.PersonalizationSection
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.components.themes_section.ThemesSection
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_tenant.feature_settings.presentation.viewmodel.SettingsViewModel
import com.example.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentSettings(
    navHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val direction = Direction(navHostController)
    val context = LocalContext.current

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            SettingsAppBar(
                settingsViewModel = settingsViewModel,
                onBackPressed = {

                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            //  accent alert dialog
            AnimatedVisibility(visible = settingsViewModel.isAccentDialogVisible.value) {
                AccentDialog(
                    coreVM = coreVM,
                    settingsViewModel = settingsViewModel,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
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
                    tertiaryColor = tertiaryColor,
                    onLogout = {
                        coreVM.onEvent(
                            CoreEvents.LogoutUser(
                            response = {
                                when (it) {
                                    is Response.Success -> {

                                        //  navigate to login screen
                                        direction.navigateToRoute(
                                            Constants.AUTHENTICATION_ROUTE,
                                            Constants.ADMIN_ROUTE
                                        )

                                        Toast.makeText(
                                            context,
                                            "Logged out successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    is Response.Failure -> {
                                        Log.d("logout", it.error.toString())
                                    }
                                }
                            }
                        ))
                    },
                    onDeleteAccount = {
                        //  delete account
                    }
                )

            }

        }

    }

}