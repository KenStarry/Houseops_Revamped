package com.example.houseops_revamped.feature_authentication.sign_up.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.feature_authentication.sign_up.domain.use_cases.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: SignUpUseCases
) : ViewModel() {


}