package com.example.houseops_revamped.feature_settings.presentation.components.themes_section

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_settings.data.datastore.ThemePreference
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.components.SectionTitle
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun ThemesSection(
    context: Context,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel
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
                title = SettingsConstants.settingsSections[0].sectionTitle,
                icon = SettingsConstants.settingsSections[0].sectionIcon,
                iconColor = SettingsConstants.settingsSections[0].sectionIconColor,
                iconBackground = SettingsConstants.settingsSections[0].sectionIconBackgroundColor,
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
                                description = it,
                                isSelected = it == savedTheme.value,
                                onRadioButtonClicked = {

                                    //  toggle radio button theme
                                    settingsViewModel.onEvent(SettingsEvents.ToggleThemeRadioBtn(it))

                                    //  toggle theme
                                    settingsViewModel.onEvent(SettingsEvents.SetTheme(it))

                                    Toast.makeText(context, "$it activated", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            )
                        }
                    },
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    modifier = Modifier
                        .height(150.dp)
                )
            }

        }

    }
}