package com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.data.repository.SignUpRepositoryImpl
import com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.repository.SignUpRepository
import com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.use_cases.CreateAccount
import com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.use_cases.CreateUserCollection
import com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.use_cases.SignUpUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {

    @Provides
    @Singleton
    fun provideSignUpRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ) : SignUpRepository = SignUpRepositoryImpl(auth, db)

    @Provides
    @Singleton
    fun provideSignUpUseCases(
        repository: SignUpRepository
    ) = SignUpUseCases(
        createAccount = CreateAccount(repository),
        createUserCollection = CreateUserCollection(repository)
    )
}
































