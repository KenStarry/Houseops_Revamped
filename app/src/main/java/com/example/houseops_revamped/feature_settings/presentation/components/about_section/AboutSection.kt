package com.example.houseops_revamped.feature_settings.presentation.components.about_section

import android.content.Context
import android.widget.Toast
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
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import com.example.houseops_revamped.feature_settings.presentation.viewmodel.SettingsViewModel

@Composable
fun AboutSection(
    context: Context,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel
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
                title = SettingsConstants.settingsSections[2].sectionTitle,
                icon = SettingsConstants.settingsSections[2].sectionIcon,
                iconColor = SettingsConstants.settingsSections[2].sectionIconColor,
                iconBackground = SettingsConstants.settingsSections[2].sectionIconBackgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

        }

    }
}