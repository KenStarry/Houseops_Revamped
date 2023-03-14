package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.themes_section

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.domain.model.SettingsEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.SectionTitle
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.utils.SettingsConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun ThemesSection(
    context: Context,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val savedTheme =
        settingsViewModel.themeFlow.collectAsState(initial = SettingsConstants.themeOptions[2])

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
                iconColor = primaryColor,
                iconBackground = tertiaryColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                settingsViewModel = settingsViewModel
            )

            //  content
            AnimatedVisibility(
                visible = settingsViewModel.isThemeSectionVisible.value,
                enter = expandIn(),
                exit = shrinkOut()
            ) {
                LazyColumn(
                    content = {
                        items(
                            items = SettingsConstants.themeOptions
                        ) {

                            ThemeRadioButton(
                                description = it.title,
                                icon = it.icon,
                                isSelected = it.title == savedTheme.value,
                                onRadioButtonClicked = {

                                    //  toggle radio button theme
                                    settingsViewModel.onEvent(SettingsEvents.ToggleThemeRadioBtn(it.title))

                                    //  toggle theme
                                    settingsViewModel.onEvent(SettingsEvents.SetTheme(it.title))

                                    Toast.makeText(context, "${it.title} activated", Toast.LENGTH_SHORT)
                                        .show()
                                },
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor
                            )
                        }
                    },
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .height((50.dp + 16.dp) * SettingsConstants.themeOptions.size)
                )
            }

        }

    }
}