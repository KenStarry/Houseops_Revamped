package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model.HouseViewEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.use_case.HouseViewUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewVM @Inject constructor(
    private val useCase: HouseViewUseCases
) : ViewModel() {

    var currentHouse by mutableStateOf<HouseModel?>(null)

    private val _apartmentDetails = mutableStateOf<Apartment?>(null)
    val apartmentDetails: State<Apartment?> = _apartmentDetails

    fun getHouse(
        apartment: String,
        category: String
    ) {
        viewModelScope.launch {

            useCase.getHouse(
                apartmentName = apartment,
                category = category,
                currentHouse = {
                    currentHouse = it
                }
            )

            Log.d("view", "$currentHouse")
        }
    }

    fun onEvent(event: HouseViewEvents) {

        when (event) {

            is HouseViewEvents.AddToBookedHouses -> {
                viewModelScope.launch {
                    useCase.addToBooked(
                        bookedHouse = event.bookedHouse,
                        email = event.email,
                        isAdd = event.isAdd
                    )
                }
            }

            is HouseViewEvents.GetApartment -> {
                viewModelScope.launch {
                    useCase.getApartment(
                        apartmentName = event.apartmentName,
                        apartment = { _apartmentDetails.value = it },
                        response = event.response
                    )
                }
            }

            is HouseViewEvents.AddUserToHouseBooked -> {
                viewModelScope.launch {
                    useCase.addUserToHouseBooked(
                        apartmentName = event.apartmentName,
                        houseCategory = event.houseCategory,
                        userBooked = event.userBooked,
                        isAdd = event.isAdd
                    )
                }
            }
        }
    }
}



















