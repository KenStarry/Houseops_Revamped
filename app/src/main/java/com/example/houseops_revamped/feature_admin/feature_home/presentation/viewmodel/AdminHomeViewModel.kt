package com.example.houseops_revamped.feature_admin.feature_home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_admin.feature_home.domain.models.AdminHomeEvents
import com.example.houseops_revamped.feature_admin.feature_home.domain.use_cases.AdminHomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor(
    private val useCases: AdminHomeUseCases
) : ViewModel() {

    private val _landlords = mutableStateOf<List<UsersCollection>>(emptyList())
    val landlords: State<List<UsersCollection>> = _landlords

    fun onEvent(event: AdminHomeEvents) {

        when (event) {

            is AdminHomeEvents.GetLandlords -> {
                viewModelScope.launch {
                    useCases.getLandlords(
                        landlords = {
                            _landlords.value = it
                        },
                        response = { res ->
                            when (res) {
                                is Response.Success -> {}
                                is Response.Failure -> {}
                            }
                        }
                    )
                }
            }
        }
    }

}