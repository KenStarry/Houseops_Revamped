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

    private val _selectedTheme = mutableStateOf(SettingsConstants.themeOptions[2])
    val selectedTheme: State<String> = _selectedTheme

    fun onEvent(event: SettingsEvents) {
        when(event) {

            is SettingsEvents.ToggleDropdownMenu -> {
                _isDropdownExpanded.value = event.isDropdownExpanded
            }

            is SettingsEvents.ToggleThemeRadioBtn -> {
                _selectedTheme.value = event.selectedTheme
            }

            is SettingsEvents.SetTheme -> {
                viewModelScope.launch {
                    themePreference.setTheme(event.theme)
                }
            }
        }
    }
}