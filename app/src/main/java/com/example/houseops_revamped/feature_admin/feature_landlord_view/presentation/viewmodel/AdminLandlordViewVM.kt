package com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.model.AdminLandlordViewEvents
import com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.use_case.AdminLandlordViewUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminLandlordViewVM @Inject constructor(
    private val useCases: AdminLandlordViewUseCases
) : ViewModel() {

    fun onEvent(event: AdminLandlordViewEvents) {

        when (event) {

            is AdminLandlordViewEvents.GetApartments -> {

            }
        }
    }
}