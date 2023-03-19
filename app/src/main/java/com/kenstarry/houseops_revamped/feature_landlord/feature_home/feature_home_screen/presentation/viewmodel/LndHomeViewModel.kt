package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases.LndHomeUseCases
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Landlord
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.model.LndHomeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LndHomeViewModel @Inject constructor(
    private val useCases: LndHomeUseCases
) : ViewModel() {

    private val _landlordDetails = mutableStateOf<UsersCollection?>(null)
    val landlordDetails: State<UsersCollection?> = _landlordDetails

    private val _landlordApartments = mutableStateOf<List<Apartment>>(listOf())
    val landlordApartments: State<List<Apartment>> = _landlordApartments

    fun onEvent(event: LndHomeEvents) {
        when (event) {

            is LndHomeEvents.GetLandlordDetails -> {
                viewModelScope.launch {
                    useCases.getLandlordDetails(
                        email = event.email,
                        landlord = {
                            _landlordDetails.value = it
                        },
                        response = event.response
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



























