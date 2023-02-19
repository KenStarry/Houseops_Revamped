package com.example.houseops_revamped.feature_settings.di

import android.content.Context
import com.example.houseops_revamped.feature_settings.data.datastore.AccentPreference
import com.example.houseops_revamped.feature_settings.data.datastore.ThemePreference
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
    fun provideThemePreference(
        @ApplicationContext context: Context
    ) = ThemePreference(context)
}