package com.example.houseops_revamped.feature_settings.presentation.components.personalization_section

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import com.example.houseops_revamped.core.presentation.model.AccentColor
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.components.SectionTitle
import com.example.houseops_revamped.feature_settings.presentation.components.danger_section.DangerItem
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel
import com.example.houseops_revamped.ui.theme.BlueAccent

@Composable
fun PersonalizationSection(
    context: Context,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel
) {

    val listState = rememberLazyListState()
    val primaryColor = settingsViewModel.primaryAccentFlow.collectAsState(
        initial = 0
    ).value ?: Constants.accentColors[1].darkColor

    val tertiaryColor = settingsViewModel.primaryAccentFlow.collectAsState(
        initial = 0
    ).value ?: Constants.accentColors[1].lightColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            SectionTitle(
                title = SettingsConstants.settingsSections[1].sectionTitle,
                icon = SettingsConstants.settingsSections[1].sectionIcon,
                iconColor = SettingsConstants.settingsSections[1].sectionIconColor,
                iconBackground = SettingsConstants.settingsSections[1].sectionIconBackgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                settingsViewModel = settingsViewModel
            )

            AnimatedVisibility(
                visible = settingsViewModel.isPersonalizationSectionVisible.value,
                enter = expandIn(),
                exit = shrinkOut()
            ) {

                LazyColumn(
                    content = {
                        items(
                            items = SettingsConstants.personalizationOptions
                        ) {
                            //  danger item
                            PersonalizationItem(
                                title = it.title,
                                icon = it.icon,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .clickable {
                                        when (it.title) {
                                            SettingsConstants.personalizationOptions[0].title -> {
                                                //  change accent color

                                                settingsViewModel.onEvent(SettingsEvents.SetAccent(
                                                    accent = AccentColor(
                                                        Constants.accentColors[2].darkColor,
                                                        Constants.accentColors[2].lightColor
                                                    )
                                                ))

                                                Constants.primaryCol.value =
                                                    Color(primaryColor)

                                                Constants.tertiaryCol.value =
                                                    Color(tertiaryColor)

                                                Toast
                                                    .makeText(
                                                        context,
                                                        "${Constants.primaryCol.value}",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            }
                                        }
                                    }
                                    .padding(
                                        end = 16.dp
                                    )
                            )
                        }
                    },
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((50.dp + 16.dp) * SettingsConstants.personalizationOptions.size),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )

            }
        }

    }
}