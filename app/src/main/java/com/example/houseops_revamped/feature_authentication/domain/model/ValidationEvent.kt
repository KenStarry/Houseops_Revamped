package com.example.houseops_revamped.feature_authentication.domain.model

sealed class ValidationEvent {
    object Success : ValidationEvent()
    object Failure : ValidationEvent()
}
