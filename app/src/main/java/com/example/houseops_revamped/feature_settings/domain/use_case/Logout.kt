package com.example.houseops_revamped.feature_settings.domain.use_case

import com.example.houseops_revamped.feature_settings.domain.repository.SettingsRepository

class Logout(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(
        onLogout: () -> Unit
    ) = repository.logout(
        onLogout = onLogout
    )
}