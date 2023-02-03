package com.example.houseops_revamped.feature_categories.presentation.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.feature_categories.domain.model.CategoryEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesViewModel : ViewModel() {

    val _bottomSheetContent = mutableStateOf("")
    val bottomSheetContent: State<String> = _bottomSheetContent

    val _caretakerData = mutableStateOf<Caretaker?>(null)
    val caretakerData: State<Caretaker?> = _caretakerData


    @OptIn(ExperimentalMaterialApi::class)
    fun onEvent(event: CategoryEvents<*>) {
        when (event) {

            is CategoryEvents.ChangeBottomSheetContent -> {
                viewModelScope.launch {
                    _bottomSheetContent.value = event.categoryTitle
                }
            }

            is CategoryEvents.OpenBottomSheet<*> -> {
                event.scope.launch {
                    event.state.animateTo(ModalBottomSheetValue.Expanded)

                    when (event.bottomSheetData) {
                        is Caretaker -> {
                            _caretakerData.value = event.bottomSheetData
                        }
                    }
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