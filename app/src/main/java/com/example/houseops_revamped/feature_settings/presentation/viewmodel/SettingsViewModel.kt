package com.example.houseops_revamped.feature_settings.presentation.viewmodel

import android.view.View
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.feature_settings.domain.model.SettingsEvents

class SettingsViewModel : ViewModel() {

    private val _isDropdownExpanded = mutableStateOf(false)
    val isDropdownExpanded: State<Boolean> = _isDropdownExpanded

    fun onEvent(event: SettingsEvents) {
        when(event) {

            is SettingsEvents.ToggleDropdownMenu -> {
                _isDropdownExpanded.value = event.isDropdownExpanded
            }
        }
    }
}