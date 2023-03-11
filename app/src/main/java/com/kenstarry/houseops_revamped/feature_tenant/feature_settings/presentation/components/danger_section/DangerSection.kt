package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.danger_section

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
fun DangerSection(
    context: Context,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel,
    primaryColor: Color,
    tertiaryColor: Color,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit
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
                iconColor = SettingsConstants.settingsSections[3].sectionIconColor,
                iconBackground = SettingsConstants.settingsSections[3].sectionIconBackgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                settingsViewModel
            )

            AnimatedVisibility(
                visible = settingsViewModel.isDangerSectionVisible.value,
                enter = expandIn(),
                exit = shrinkOut()
            ) {

                LazyColumn(
                    content = {
                        items(
                            items = SettingsConstants.dangerOptions
                        ) {
                            //  danger item
                            DangerItem(
                                title = it.title,
                                icon = it.icon,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .clickable {
                                        when (it.title) {
                                            SettingsConstants.dangerOptions[0].title -> {
                                                //  logout
                                                onLogout()
                                            }

                                            SettingsConstants.dangerOptions[1].title -> {
                                                //  delete account
                                                onDeleteAccount()
                                            }
                                        }
                                    }
                            )
                        }
                    },
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((50.dp + 16.dp) * SettingsConstants.dangerOptions.size),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )

            }

        }

    }
}