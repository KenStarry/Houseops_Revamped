package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.di

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.data.repository.LoginRepositoryImpl
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.repository.LoginRepository
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.*
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.validation.LoginValidateEmail
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.use_cases.validation.LoginValidateUseCases
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
        auth: FirebaseAuth,
        gso: GoogleSignInOptions
    ): LoginRepository = LoginRepositoryImpl(auth, gso)

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: LoginRepository
    ) = LoginUseCases(
        login = Login(repository),
        loginWithGoogle = LoginWithGoogle(repository),
        firebaseAuthWithGoogle = FirebaseAuthWithGoogle(repository),
        passwordResetEmail = PasswordResetEmail(repository)
    )

    @Provides
    @Singleton
    fun provideLoginValidateUseCases() = LoginValidateUseCases(
        loginValidateEmail = LoginValidateEmail()
    )
}



















