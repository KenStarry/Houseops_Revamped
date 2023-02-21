package com.example.houseops_revamped.feature_authentication.di

import com.example.houseops_revamped.feature_authentication.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideValidateUseCases() = ValidateUseCases(
        validateEmail = ValidateEmail(),
        validateUserName = ValidateUserName(),
        validatePassword = ValidatePassword(),
        validateRepeatedPassword = ValidateRepeatedPassword()
    )
}