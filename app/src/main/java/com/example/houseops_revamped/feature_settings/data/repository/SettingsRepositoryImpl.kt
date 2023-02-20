package com.example.houseops_revamped.feature_settings.data.repository

import android.util.Log
import com.example.houseops_revamped.feature_settings.domain.repository.SettingsRepository
import com.google.firebase.auth.FirebaseAuth
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