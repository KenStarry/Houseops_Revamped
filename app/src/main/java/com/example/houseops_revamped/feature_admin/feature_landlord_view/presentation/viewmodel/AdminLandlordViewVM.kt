package com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.model.AdminLandlordViewEvents
import com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.use_case.AdminLandlordViewUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminLandlordViewVM @Inject constructor(
    private val useCases: AdminLandlordViewUseCases
) : ViewModel() {

    private val _apartments = mutableStateOf<List<Apartment>>(emptyList())
    val apartments: State<List<Apartment>> = _apartments

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
        }
    }
}