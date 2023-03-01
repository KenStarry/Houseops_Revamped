package com.example.houseops_revamped.feature_settings.presentation.components.alert_dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.components.CustomAlertDialog
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun AccentDialog(
    coreVM: CoreViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel,
    primaryColor: Color,
    tertiaryColor: Color,
) {

    val gridListState = rememberLazyGridState()

    CustomAlertDialog(
        icon = SettingsConstants.settingsSections[1].sectionIcon,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        title = "Pick Accent Color",
        content = {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                content = {
                    items(Constants.accentColors) { accentColor ->
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(35.dp)
                                .background(Color(accentColor.darkColor))
                                .border(
                                    width = 5.dp,
                                    color = Color(accentColor.lightColor),
                                    shape = CircleShape
                                )
                                .clickable {
                                    //  change accent color
                                    coreVM.onEvent(
                                        CoreEvents.ChangeAccent(
                                            accentColor = accentColor
                                        )
                                    )

                                    // close the alert dialog
                                    settingsViewModel.onEvent(
                                        SettingsEvents.ToggleAlertDialog(
                                            alertType = SettingsConstants.ACCENT_ALERT_DIALOG,
                                            isVisible = false
                                        )
                                    )
                                }
                        )
                    }
                },
                state = gridListState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
        },
        onConfirm = {
            // close the alert dialog
            settingsViewModel.onEvent(
                SettingsEvents.ToggleAlertDialog(
                    alertType = SettingsConstants.ACCENT_ALERT_DIALOG,
                    isVisible = false
                )
            )
        },
        onDismiss = {
            // close the alert dialog
            settingsViewModel.onEvent(
                SettingsEvents.ToggleAlertDialog(
                    alertType = SettingsConstants.ACCENT_ALERT_DIALOG,
                    isVisible = false
                )
            )
        }
    )
}