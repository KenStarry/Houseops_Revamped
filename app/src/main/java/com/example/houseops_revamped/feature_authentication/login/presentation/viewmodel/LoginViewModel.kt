package com.example.houseops_revamped.feature_authentication.login.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_authentication.login.domain.model.LoginEvents
import com.example.houseops_revamped.feature_authentication.login.domain.use_cases.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCases
) : ViewModel() {

    var loginResponse by mutableStateOf<Response>(Response.Failure)
        private set

    fun onEvent(event: LoginEvents) {

        when (event) {

            is LoginEvents.Login -> {
                viewModelScope.launch {

                    useCase.login(
                        email = event.email,
                        password = event.password,
                        onResponse = { response ->
                            response?.let { loginResponse = it }

                            Log.d("response", response.toString())
                        }
                    )

                }
            }

        }
    }
}