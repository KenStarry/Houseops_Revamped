package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.use_case.BookedUseCases
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookedViewModel @Inject constructor(
    private val useCase: BookedUseCases
) : ViewModel() {

    private val _bookedHouses = mutableStateOf<List<HouseModel>>(emptyList())
    val bookedHouses: State<List<HouseModel>> = _bookedHouses

    fun getBookedHouses(
        houseIds: List<String>
    ) {
        Log.d("bookedVM", "viewmodel reached")


        viewModelScope.launch {
            useCase.getBookedHouses(
                houseIds = houseIds,
                bookedHouses = {
                    _bookedHouses.value = it

                    Log.d("bookedVM", "all booked houses = ${it.size}")
                }
            )
        }

    }
    fun onEvent(event: BookedEvents) {
        when(event) {

            is BookedEvents.GetBookedHouses -> {
                viewModelScope.launch {
                    useCase.getBookedHouses(
                        houseIds = event.houseIds,
                        bookedHouses = {
                            _bookedHouses.value = it

                            Log.d("bookedVM", "all booked houses = ${it.size}")
                        }
                    )
                }
            }
        }
    }
}