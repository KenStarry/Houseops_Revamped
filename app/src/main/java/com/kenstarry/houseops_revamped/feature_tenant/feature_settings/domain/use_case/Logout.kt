package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.domain.use_case

import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.domain.repository.SettingsRepository

class Logout(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(
        onLogout: () -> Unit
    ) = repository.logout(
        onLogout = onLogout
    )
}