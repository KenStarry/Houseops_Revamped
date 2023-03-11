package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    val auth: FirebaseAuth
) : SettingsRepository {

    override suspend fun logout(
        onLogout: () -> Unit
    ) {
        try {
            auth.signOut()
            onLogout()
        } catch (e: Exception) {
            Log.d("Signout", "$e")
        }
    }

    override suspend fun deleteAccount() {

    }
}