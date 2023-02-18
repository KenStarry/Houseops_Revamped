package com.example.houseops_revamped.feature_settings.presentation.components.personalization_section

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
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
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.components.SectionTitle
import com.example.houseops_revamped.feature_settings.presentation.components.danger_section.DangerItem
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun PersonalizationSection(
    context: Context,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel
) {

    val listState = rememberLazyListState()

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