package com.example.houseops_revamped.feature_settings.domain.model

sealed class SettingsEvents {

    data class ToggleDropdownMenu(
        val isDropdownExpanded: Boolean
    ) : SettingsEvents()
}
