package com.example.houseops_revamped.feature_home.house_view_screen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.feature_home.house_view_screen.domain.use_case.HouseViewUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HouseViewVM @Inject constructor(
    private val houseViewUseCases: HouseViewUseCases
) : ViewModel() {


}