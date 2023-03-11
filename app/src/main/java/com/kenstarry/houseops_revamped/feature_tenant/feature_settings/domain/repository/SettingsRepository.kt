package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.domain.repository

interface SettingsRepository {

    suspend fun logout(
        onLogout: () -> Unit
    )

    suspend fun deleteAccount()

}