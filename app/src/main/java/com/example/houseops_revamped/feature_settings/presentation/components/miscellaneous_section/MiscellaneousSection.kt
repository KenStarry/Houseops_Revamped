package com.example.houseops_revamped.feature_settings.presentation.components.miscellaneous_section

import android.content.Context
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
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_settings.presentation.components.SectionTitle
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun MiscellaneousSection(
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
                title = SettingsConstants.settingsSections[2].sectionTitle,
                icon = SettingsConstants.settingsSections[2].sectionIcon,
                iconColor = SettingsConstants.settingsSections[2].sectionIconColor,
                iconBackground = SettingsConstants.settingsSections[2].sectionIconBackgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                settingsViewModel = settingsViewModel
            )

            AnimatedVisibility(
                visible = settingsViewModel.isMiscSectionVisible.value,
                enter = expandIn(),
                exit = shrinkOut()
            ) {

                LazyColumn(
                    content = {
                        items(
                            items = SettingsConstants.miscOptions
                        ) {
                            //  danger item
                            MiscellaneousItem(
                                title = it.title,
                                icon = it.icon,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            )
                        }
                    },
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((50.dp + 16.dp) * SettingsConstants.miscOptions.size),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )

            }

        }

    }
}