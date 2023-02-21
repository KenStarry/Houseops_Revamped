package com.example.houseops_revamped.feature_authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.feature_authentication.domain.use_cases.ValidateUseCases
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val useCases: ValidateUseCases
) : ViewModel() {


}