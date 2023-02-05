package com.example.houseops_revamped.feature_settings.presentation.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsSectionModel(
    val sectionTitle: String,
    val sectionIcon: ImageVector,
    val sectionIconColor: Color,
    val sectionIconBackgroundColor: Color
)
