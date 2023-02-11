package com.example.houseops_revamped.feature_booked.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.feature_booked.domain.model.BookedEvents
import com.example.houseops_revamped.feature_booked.domain.use_case.BookedUseCases
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class BookedViewModel(
    private val useCase: BookedUseCases
) : ViewModel() {

    private val _bookedHouses = mutableStateOf<List<HouseModel>>(emptyList())
    val bookedHouses: State<List<HouseModel>> = _bookedHouses

    fun onEvent(event: BookedEvents) {
        when(event) {

            is BookedEvents.GetBookedHouses -> {
                viewModelScope.launch {
                    useCase.getBookedHouses(
                        houseIds = event.houseIds,
                        bookedHouses = {
                            _bookedHouses.value = it
                        }
                    )
                }
            }
        }
    }
}