package com.example.houseops_revamped.feature_settings.domain.model

sealed class SettingsEvents {

    data class ToggleDropdownMenu(
        val isDropdownExpanded: Boolean
    ) : SettingsEvents()

    data class ToggleThemeRadioBtn(
        val selectedTheme: String
    ) : SettingsEvents()

    data class SetTheme(val theme: String) : SettingsEvents()

}
