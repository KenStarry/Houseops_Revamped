package com.example.houseops_revamped.di

import com.example.houseops_revamped.feature_authentication.login.domain.repository.LoginRepository
import com.example.houseops_revamped.feature_authentication.login.domain.use_cases.Login
import com.example.houseops_revamped.feature_authentication.login.domain.use_cases.LoginUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: LoginRepository
    ) = LoginUseCases(
        login = Login(repository)
    )
}