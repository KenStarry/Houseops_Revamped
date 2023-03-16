package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.domain.model.SettingsEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.utils.SettingsConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SectionTitle(
    title: String,
    icon: ImageVector,
    iconColor: Color,
    iconBackground: Color,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel
) {

    var isSectionVisible by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .clickable {
                settingsViewModel.onEvent(
                    SettingsEvents.ToggleSectionVisibility(
                        sectionTitle = title,
                        isSectionVisible = when(title) {

                            SettingsConstants.settingsSections[0].sectionTitle -> {
                                val visibility = !settingsViewModel.isDashboardSectionVisible.value
                                isSectionVisible = visibility
                                visibility
                            }

                            SettingsConstants.settingsSections[1].sectionTitle -> {
                                val visibility = !settingsViewModel.isThemeSectionVisible.value
                                isSectionVisible = visibility
                                visibility
                            }

                            SettingsConstants.settingsSections[2].sectionTitle -> {
                                val visibility = !settingsViewModel.isPersonalizationSectionVisible.value
                                isSectionVisible = visibility
                                visibility
                            }

                            SettingsConstants.settingsSections[3].sectionTitle -> {
                                val visibility = !settingsViewModel.isMiscSectionVisible.value
                                isSectionVisible = visibility
                                visibility
                            }

                            SettingsConstants.settingsSections[4].sectionTitle -> {
                                val visibility = !settingsViewModel.isDangerSectionVisible.value
                                isSectionVisible = visibility
                                visibility
                            }
                            else -> {
                                false
                            }
                        }
                    )
                )
            },
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
                                isSectionVisible = !settingsViewModel.isDashboardSectionVisible.value
                            )
                        )

                        isSectionVisible = settingsViewModel.isDashboardSectionVisible.value
                    }

                    SettingsConstants.settingsSections[1].sectionTitle -> {
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleSectionVisibility(
                                sectionTitle = title,
                                isSectionVisible = !settingsViewModel.isThemeSectionVisible.value
                            )
                        )

                        isSectionVisible = settingsViewModel.isThemeSectionVisible.value
                    }

                    SettingsConstants.settingsSections[2].sectionTitle -> {
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleSectionVisibility(
                                sectionTitle = title,
                                isSectionVisible = !settingsViewModel.isPersonalizationSectionVisible.value
                            )
                        )

                        isSectionVisible = settingsViewModel.isPersonalizationSectionVisible.value
                    }

                    SettingsConstants.settingsSections[3].sectionTitle -> {
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleSectionVisibility(
                                sectionTitle = title,
                                isSectionVisible = !settingsViewModel.isMiscSectionVisible.value
                            )
                        )

                        isSectionVisible = settingsViewModel.isMiscSectionVisible.value
                    }

                    SettingsConstants.settingsSections[4].sectionTitle -> {
                        settingsViewModel.onEvent(
                            SettingsEvents.ToggleSectionVisibility(
                                sectionTitle = title,
                                isSectionVisible = !settingsViewModel.isDangerSectionVisible.value
                            )
                        )

                        isSectionVisible = settingsViewModel.isDangerSectionVisible.value
                    }
                }
            }) {
                Icon(

                    imageVector = if (isSectionVisible)
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





















