package com.example.houseops_revamped.feature_authentication.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
