package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.domain.model

import android.content.Context

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

    data class GooglePlayRating(
        val context: Context,
        val appUrl: String
    ) : SettingsEvents()

}
