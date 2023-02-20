package com.example.houseops_revamped.feature_settings.domain.repository

interface SettingsRepository {

    suspend fun logout(
        onLogout: () -> Unit
    )

    suspend fun deleteAccount()

}