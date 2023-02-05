package com.example.houseops_revamped.feature_settings.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents
import com.example.houseops_revamped.feature_settings.presentation.utils.SettingsConstants

class SettingsViewModel : ViewModel() {

    private val _isDropdownExpanded = mutableStateOf(false)
    val isDropdownExpanded: State<Boolean> = _isDropdownExpanded

    private val _selectedTheme = mutableStateOf(SettingsConstants.themeOptions[0])
    val selectedTheme: State<String> = _selectedTheme

    fun onEvent(event: SettingsEvents) {
        when(event) {

            is SettingsEvents.ToggleDropdownMenu -> {
                _isDropdownExpanded.value = event.isDropdownExpanded
            }

            is SettingsEvents.ToggleThemeRadioBtn -> {
                _selectedTheme.value = event.selectedTheme
            }
        }
    }
}