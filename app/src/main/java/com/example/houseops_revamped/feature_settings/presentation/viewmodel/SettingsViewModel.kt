package com.example.houseops_revamped.feature_settings.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.feature_settings.data.datastore.ThemePreference
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val themePreference: ThemePreference
) : ViewModel() {

    val themeFlow: Flow<String?> get() = themePreference.getTheme

    private val _isDropdownExpanded = mutableStateOf(false)
    val isDropdownExpanded: State<Boolean> = _isDropdownExpanded

    private val _selectedTheme = mutableStateOf(SettingsConstants.themeOptions[2].title)
    val selectedTheme: State<String> = _selectedTheme

    private val _isThemeSectionVisible = mutableStateOf(false)
    val isThemeSectionVisible: State<Boolean> = _isThemeSectionVisible

    private val _isPersonalizationSectionVisible = mutableStateOf(false)
    val isPersonalizationSectionVisible: State<Boolean> = _isPersonalizationSectionVisible

    private val _isAboutSectionVisible = mutableStateOf(false)
    val isAboutSectionVisible: State<Boolean> = _isAboutSectionVisible

    private val _isDangerSectionVisible = mutableStateOf(false)
    val isDangerSectionVisible: State<Boolean> = _isDangerSectionVisible

    fun onEvent(event: SettingsEvents) {
        when (event) {

            is SettingsEvents.ToggleDropdownMenu -> {
                _isDropdownExpanded.value = event.isDropdownExpanded
            }

            is SettingsEvents.ToggleThemeRadioBtn -> {
                _selectedTheme.value = event.selectedTheme
            }

            is SettingsEvents.ToggleSectionVisibility -> {

                when (event.sectionTitle) {

                    SettingsConstants.settingsSections[0].sectionTitle -> {
                        _isThemeSectionVisible.value = event.isSectionVisible
                    }

                    SettingsConstants.settingsSections[1].sectionTitle -> {
                        _isPersonalizationSectionVisible.value = event.isSectionVisible
                    }

                    SettingsConstants.settingsSections[2].sectionTitle -> {
                        _isAboutSectionVisible.value = event.isSectionVisible
                    }

                    SettingsConstants.settingsSections[3].sectionTitle -> {
                        _isDangerSectionVisible.value = event.isSectionVisible
                    }
                }
            }

            is SettingsEvents.SetTheme -> {
                viewModelScope.launch {
                    themePreference.setTheme(event.theme)
                }
            }
        }
    }
}