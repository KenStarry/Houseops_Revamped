package com.example.houseops_revamped.feature_home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.feature_home.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.domain.use_cases.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel() {

    var houses by mutableStateOf<ArrayList<HouseModel>>(ArrayList())
        private set

    init {
        getHouses()
    }

    fun getHouses() {

        viewModelScope.launch {
            useCases.getHouses(
                houses = {

                    it.forEach { house ->
                        if (!houses.contains(house))
                            houses.add(house)
                    }
                }
            )
        }
    }
}