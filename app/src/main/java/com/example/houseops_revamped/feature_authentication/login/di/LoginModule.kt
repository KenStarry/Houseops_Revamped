package com.example.houseops_revamped.feature_authentication.login.di

import com.example.houseops_revamped.feature_authentication.login.data.repository.LoginRepositoryImpl
import com.example.houseops_revamped.feature_authentication.login.domain.repository.LoginRepository
import com.example.houseops_revamped.feature_authentication.login.domain.use_cases.Login
import com.example.houseops_revamped.feature_authentication.login.domain.use_cases.LoginUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        auth: FirebaseAuth
    ) : LoginRepository = LoginRepositoryImpl(auth)

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: LoginRepository
    ) = LoginUseCases(
        login = Login(repository)
    )
}



















