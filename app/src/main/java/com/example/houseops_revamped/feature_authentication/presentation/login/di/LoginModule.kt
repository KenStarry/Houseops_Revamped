package com.example.houseops_revamped.feature_authentication.presentation.login.di

import com.example.houseops_revamped.feature_authentication.presentation.login.data.repository.LoginRepositoryImpl
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.repository.LoginRepository
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.Login
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.LoginUseCases
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.PasswordResetEmail
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.validation.LoginValidateEmail
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.validation.LoginValidateUseCases
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
        login = Login(repository),
        passwordResetEmail = PasswordResetEmail(repository)
    )

    @Provides
    @Singleton
    fun provideLoginValidateUseCases() = LoginValidateUseCases(
        loginValidateEmail = LoginValidateEmail()
    )
}



















