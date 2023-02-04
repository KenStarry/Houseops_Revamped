package com.example.houseops_revamped.feature_home.house_view_screen.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.house_view_screen.domain.model.HouseViewEvents
import com.example.houseops_revamped.feature_home.house_view_screen.domain.use_case.HouseViewUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewVM @Inject constructor(
    private val useCase: HouseViewUseCases
) : ViewModel() {

    var currentHouse by mutableStateOf<HouseModel?>(null)

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
                        houseId = event.houseId,
                        email = event.email,
                        isAdd = event.isAdd
                    )
                }
            }

            is HouseViewEvents.AddUserToHouseBooked -> {
                viewModelScope.launch {
                    useCase.addUserToHouseBooked(
                        apartmentName = event.apartmentName,
                        houseCategory = event.apartmentName,
                        userEmail = event.userEmail,
                        isAdd = event.isAdd
                    )
                }
            }
        }
    }
}



















