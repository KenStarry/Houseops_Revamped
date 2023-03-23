package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.miscellaneous_section

import android.content.Context
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.SectionTitle
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.utils.SettingsConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun MiscellaneousSection(
    context: Context,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel,
    primaryColor: Color,
    tertiaryColor: Color,
    onAbout: () -> Unit,
    onFeedback: () -> Unit,
    onShare: () -> Unit,
    onRate: () -> Unit
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
                title = SettingsConstants.settingsSections[3].sectionTitle,
                icon = SettingsConstants.settingsSections[3].sectionIcon,
                iconColor = primaryColor,
                iconBackground = tertiaryColor,
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
                                    .clickable {
                                        when (it) {
                                            SettingsConstants.miscOptions[0] -> {
                                                onAbout()
                                            }
                                            SettingsConstants.miscOptions[1] -> {
                                                onFeedback()
                                            }
                                            SettingsConstants.miscOptions[2] -> {
                                                onShare()
                                            }
                                            SettingsConstants.miscOptions[3] -> {
                                                onRate()
                                            }
                                        }
                                    },
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor
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