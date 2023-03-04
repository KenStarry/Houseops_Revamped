package com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Landlord
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.model.LndHomeEvents
import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseopscaretakers.feature_landlord.feature_home.feature_home_screen.domain.use_cases.LndHomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LndHomeViewModel @Inject constructor(
    private val useCases: LndHomeUseCases
) : ViewModel() {

    private val _landlordDetails = mutableStateOf<Landlord?>(null)
    val landlordDetails: State<Landlord?> = _landlordDetails

    private val _landlordApartments = mutableStateOf<List<Apartment>>(listOf())
    val landlordApartments: State<List<Apartment>> = _landlordApartments

    fun onEvent(event: LndHomeEvents) {
        when (event) {

            is LndHomeEvents.GetLandlordDetails -> {
                viewModelScope.launch {
                    useCases.getLandlordDetails(
                        email = event.email,
                        landlord = {

                        },
                        response = {
                            when (it) {
                                is Response.Success -> {
                                    _landlordDetails.value = it.data as Landlord
                                }
                                is Response.Failure -> {}
                            }
                        }
                    )
                }
            }

            is LndHomeEvents.GetLandlordApartments -> {
                viewModelScope.launch {
                    useCases.getLandlordApartments(
                        email = event.email,
                        apartments = {
                            _landlordApartments.value = it
                        },
                        response = {}
                    )
                }
            }

            is LndHomeEvents.FilterGreetingsText -> {
                when (event.currentHour) {
                    in 0..12 -> {
                        event.greetings("Good Morning", Icons.Outlined.WbSunny)
                    }
                    in 12..16 -> {
                        event.greetings("Good Afternoon", Icons.Outlined.WbCloudy)
                    }
                    else -> {
                        event.greetings("Good Evening", Icons.Outlined.ModeNight)
                    }
                }
            }
        }
    }
}



























