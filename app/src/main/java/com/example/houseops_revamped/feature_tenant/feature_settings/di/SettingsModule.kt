package com.example.houseops_revamped.feature_tenant.feature_settings.di

import android.content.Context
import com.example.houseops_revamped.feature_tenant.feature_settings.data.datastore.ThemePreference
import com.example.houseops_revamped.feature_tenant.feature_settings.data.repository.SettingsRepositoryImpl
import com.example.houseops_revamped.feature_tenant.feature_settings.domain.repository.SettingsRepository
import com.example.houseops_revamped.feature_tenant.feature_settings.domain.use_case.Logout
import com.example.houseops_revamped.feature_tenant.feature_settings.domain.use_case.SettingsUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(
        auth: FirebaseAuth
    ) : SettingsRepository = SettingsRepositoryImpl(auth)

    @Provides
    @Singleton
    fun provideSettingsUseCases(
        repository: SettingsRepository
    ) = SettingsUseCases(
        logout = Logout(repository)
    )

    @Provides
    @Singleton
    fun provideThemePreference(
        @ApplicationContext context: Context
    ) = ThemePreference(context)
}