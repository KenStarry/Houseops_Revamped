package com.kenstarry.houseops_revamped.feature_tenant.feature_settings

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.domain.model.SettingsEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.SettingsAppBar
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.alert_dialogs.AccentDialog
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.danger_section.DangerSection
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.miscellaneous_section.MiscellaneousSection
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.personalization_section.PersonalizationSection
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.profile_section.ProfileSection
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.themes_section.ThemesSection
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.utils.SettingsConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.viewmodel.SettingsViewModel
import com.kenstarry.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(navHostController)

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

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

    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            SettingsAppBar(
                onBackPressed = {},
                settingsViewModel = settingsViewModel,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                //  user profile section
                ProfileSection(
                    userDetails = userDetails,
                    context = context,
                    settingsViewModel = settingsViewModel,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    modifier = Modifier
                        .wrapContentSize()
                )

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
                    tertiaryColor = tertiaryColor,
                    onLogout = {
                        settingsViewModel.onEvent(SettingsEvents.Logout(
                            onLogout = {

                                //  navigate to login screen
                                direction.navigateToRoute(
                                    Constants.AUTHENTICATION_ROUTE,
                                    Constants.HOME_ROUTE
                                )

                                Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
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











