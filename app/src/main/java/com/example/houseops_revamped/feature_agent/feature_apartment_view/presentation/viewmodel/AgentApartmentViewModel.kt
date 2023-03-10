package com.example.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.model.ImagesState
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases.AgentApartmentUseCases
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentApartmentViewModel @Inject constructor(
    private val useCases: AgentApartmentUseCases
) : ViewModel() {

    private val _apartmentHouses = mutableStateOf<List<HouseModel>>(emptyList())
    val apartmentHouses: State<List<HouseModel>> = _apartmentHouses

    var selectedImagesState by mutableStateOf(ImagesState())
        private set

    fun onEvent(event: AgentApartmentEvents) {
        when (event) {

            is AgentApartmentEvents.GetApartmentHouses -> {
                viewModelScope.launch {
                    useCases.getApartmentHouses(
                        apartmentName = event.apartmentName,
                        houses = {
                            _apartmentHouses.value = it
                        },
                        onResponse = event.onResponse
                    )
                }
            }
            is AgentApartmentEvents.AddGalleryImages -> {}

            is AgentApartmentEvents.DeleteImageFromList -> {
                val updatedImageList = selectedImagesState.listOfSelectedImages.toMutableList()

                viewModelScope.launch {
                    updatedImageList.removeAt(event.index)
                    selectedImagesState = selectedImagesState.copy(
                        listOfSelectedImages = updatedImageList.distinct()
                    )
                }
            }

            is AgentApartmentEvents.UpdateGalleryImages -> {
                val updatedImageList = selectedImagesState.listOfSelectedImages.toMutableList()

                viewModelScope.launch {
                    updatedImageList += event.uris
                    selectedImagesState = selectedImagesState.copy(
                        listOfSelectedImages = updatedImageList.distinct()
                    )
                }
            }
        }
    }
}
























