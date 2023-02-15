package com.example.houseops_revamped.feature_settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SectionTitle(
    title: String,
    icon: ImageVector,
    iconColor: Color,
    iconBackground: Color,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  section icon & title
        Row(
            modifier = Modifier
                .weight(4f),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //  section icon
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(45.dp)
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "section icon",
                    tint = iconColor
                )
            }

            //  section title
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {

            IconButton(onClick = {
                when (title) {

                    SettingsConstants.settingsSections[0].sectionTitle -> {
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleSectionVisibility(
                                sectionTitle = title,
                                isSectionVisible = !settingsViewModel.isThemeSectionVisible.value
                            )
                        )
                    }

                    SettingsConstants.settingsSections[1].sectionTitle -> {
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleSectionVisibility(
                                sectionTitle = title,
                                isSectionVisible = !settingsViewModel.isPersonalizationSectionVisible.value
                            )
                        )
                    }

                    SettingsConstants.settingsSections[2].sectionTitle -> {
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleSectionVisibility(
                                sectionTitle = title,
                                isSectionVisible = !settingsViewModel.isAboutSectionVisible.value
                            )
                        )
                    }

                    SettingsConstants.settingsSections[3].sectionTitle -> {
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleSectionVisibility(
                                sectionTitle = title,
                                isSectionVisible = !settingsViewModel.isDangerSectionVisible.value
                            )
                        )
                    }
                }
            }) {
                Icon(
                    imageVector = if (settingsViewModel.isThemeSectionVisible.value)
                        Icons.Outlined.ArrowDropUp
                    else
                        Icons.Outlined.ArrowDropDown,
                    contentDescription = "dropdown arrow",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                )
            }

        }
    }
}





















