package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.FeaturesState
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.ImagesState
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.use_cases.AgentApartmentUseCases
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.model.ApartmentHouseFeaturesModel
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

    var selectedFeaturesState by mutableStateOf(FeaturesState())
        private set

    private val _selectedHouseCategory = mutableStateOf("Pick House Category")
    val selectedHouseCategory: State<String> = _selectedHouseCategory

    private val _selectedHousePrice = mutableStateOf("")
    val selectedHousePrice: State<String> = _selectedHousePrice

    private val _selectedVacantUnits = mutableStateOf(0)
    val selectedVacantUnits: State<Int> = _selectedVacantUnits

    private val _selectedHouseDescription = mutableStateOf("")
    val selectedHouseDescription: State<String> = _selectedHouseDescription

    fun onEvent(event: AgentApartmentEvents) {
        when (event) {

            is AgentApartmentEvents.AddHouse -> {
                viewModelScope.launch {
                    useCases.addHouseToFirestore(
                        apartmentName = event.apartmentName,
                        houseModel = event.houseModel,
                        onResponse = event.onResponse
                    )
                }
            }

            is AgentApartmentEvents.UpdateHouse -> {
                viewModelScope.launch {
                    useCases.updateHouseInFirestore(
                        apartmentName = event.apartmentName,
                        houseModel = event.houseModel,
                        onResponse = event.onResponse
                    )
                }
            }

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

            is AgentApartmentEvents.ClearGalleryImages -> {
                val updatedImagesList = selectedImagesState.listOfSelectedImages.toMutableList()

                viewModelScope.launch {
                    updatedImagesList.clear()

                    selectedImagesState = selectedImagesState.copy(
                        listOfSelectedImages = updatedImagesList
                    )
                }
            }

            is AgentApartmentEvents.AddFeature -> {
                val updatedFeaturesList = selectedFeaturesState.listOfSelectedFeatures.toMutableList()

                viewModelScope.launch {
                    updatedFeaturesList += event.feature
                    selectedFeaturesState = selectedFeaturesState.copy(
                        listOfSelectedFeatures = updatedFeaturesList.distinct()
                    )
                }
            }

            is AgentApartmentEvents.DeleteFeature -> {
                val updatedFeaturesList = selectedFeaturesState.listOfSelectedFeatures.toMutableList()

                viewModelScope.launch {
                    updatedFeaturesList.remove(event.feature)
                    selectedFeaturesState = selectedFeaturesState.copy(
                        listOfSelectedFeatures = updatedFeaturesList.distinct()
                    )
                }
            }

            is AgentApartmentEvents.ClearSelectedFeatures -> {
                val updatedFeaturesList = selectedFeaturesState.listOfSelectedFeatures.toMutableList()

                viewModelScope.launch {
                    updatedFeaturesList.clear()
                    selectedFeaturesState = selectedFeaturesState.copy(
                        listOfSelectedFeatures = updatedFeaturesList
                    )
                }
            }

            is AgentApartmentEvents.SelectHouseCategory -> {
                _selectedHouseCategory.value = event.category
            }

            is AgentApartmentEvents.SelectHousePrice -> {
                _selectedHousePrice.value = event.price
            }

            is AgentApartmentEvents.SelectVacantUnits -> {
                _selectedVacantUnits.value = event.count
            }

            is AgentApartmentEvents.SelectHouseDescription -> {
                _selectedHouseDescription.value = event.description
            }
        }
    }
}
























