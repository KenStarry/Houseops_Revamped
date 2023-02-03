package com.example.houseops_revamped.feature_categories.presentation.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.feature_categories.domain.model.CategoryEvents
import com.example.houseops_revamped.feature_categories.domain.use_case.CategoriesUseCases
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val useCases: CategoriesUseCases
) : ViewModel() {

    val _bottomSheetContent = mutableStateOf("")
    val bottomSheetContent: State<String> = _bottomSheetContent

    val _caretakerData = mutableStateOf<Caretaker?>(null)
    val caretakerData: State<Caretaker?> = _caretakerData

    val _caretakerHouses = mutableStateOf<List<HouseModel>>(emptyList())
    val caretakerHouses: State<List<HouseModel>> = _caretakerHouses

    fun getCaretakerHouses(
        apartmentName: String
    ): List<HouseModel> {
        viewModelScope.launch {
            useCases.getCaretakerHouses(
                apartmentName = apartmentName,
                houses = {
                    _caretakerHouses.value = it
                }
            )
        }

        return caretakerHouses.value
    }

//    fun addSuffixSToWord(count: Int, word: String): String {
//        if (count < 2) {
//
//        }
//    }

    @OptIn(ExperimentalMaterialApi::class)
    fun onEvent(event: CategoryEvents<*>) {
        when (event) {

            is CategoryEvents.OpenBottomSheet<*> -> {
                event.scope.launch {

                    event.state.animateTo(ModalBottomSheetValue.Expanded)

                    when (event.bottomSheetData) {
                        is Caretaker -> {
                            _caretakerData.value = event.bottomSheetData
                        }
                    }
                }

                viewModelScope.launch {
                    _bottomSheetContent.value = event.bottomSheetType
                }
            }

            is CategoryEvents.CloseBottomSheet -> {
                event.scope.launch {
                    event.state.animateTo(ModalBottomSheetValue.Hidden)
                }
            }
        }
    }
}