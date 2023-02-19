package com.example.houseops_revamped.feature_settings.domain.repository

interface SettingsRepository {

    suspend fun logout()

    suspend fun deleteAccount()

}