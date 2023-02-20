package com.example.houseops_revamped.feature_settings.domain.model

import com.example.houseops_revamped.core.presentation.model.AccentColor

sealed class SettingsEvents {

    data class ToggleDropdownMenu(
        val isDropdownExpanded: Boolean
    ) : SettingsEvents()

    data class ToggleThemeRadioBtn(
        val selectedTheme: String
    ) : SettingsEvents()

    data class ToggleSectionVisibility(
        val sectionTitle: String,
        val isSectionVisible: Boolean
    ) : SettingsEvents()

    data class ToggleAlertDialog(
        val alertType: String,
        val isVisible: Boolean
    ) : SettingsEvents()

    data class SetTheme(val theme: String) : SettingsEvents()

    data class Logout(
        val onLogout: () -> Unit
    ) : SettingsEvents()

}
