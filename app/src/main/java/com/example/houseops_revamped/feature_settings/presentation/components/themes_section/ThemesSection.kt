package com.example.houseops_revamped.feature_settings.presentation.components.themes_section

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.feature_settings.presentation.components.SectionTitle
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants

@Composable
fun ThemesSection(
    modifier: Modifier = Modifier
) {

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
                    .wrapContentHeight()
            )

        }

    }
}