package com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.domain.model.AdminLandlordViewEvents
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.domain.use_case.AdminLandlordViewUseCases
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.utils.AdminLandlordConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminLandlordViewVM @Inject constructor(
    private val useCases: AdminLandlordViewUseCases
) : ViewModel() {

    private val _apartments = mutableStateOf<List<Apartment>>(emptyList())
    val apartments: State<List<Apartment>> = _apartments

    private val _isAssignAlertDialogVisible = mutableStateOf<Boolean>(false)
    val isAssignAlertDialogVisible: State<Boolean> = _isAssignAlertDialogVisible

    private val _selectedAgent = mutableStateOf<UsersCollection?>(null)
    val selectedAgent: State<UsersCollection?> = _selectedAgent

    fun onEvent(event: AdminLandlordViewEvents) {

        when (event) {

            is AdminLandlordViewEvents.GetApartments -> {
                viewModelScope.launch {
                    useCases.getApartments(
                        landlordEmail = event.landlordEmail,
                        apartments = {
                            _apartments.value = it
                        },
                        response = event.response
                    )
                }
            }

            is AdminLandlordViewEvents.ToggleAgentSelected -> {
                _selectedAgent.value = event.selectedAgent
            }

            is AdminLandlordViewEvents.ToggleAlertDialog -> {
                when (event.dialogType) {
                    AdminLandlordConstants.ASSIGN_AGENT_DIALOG -> {
                        _isAssignAlertDialogVisible.value = event.isVisible
                    }
                }
            }
        }
    }
}