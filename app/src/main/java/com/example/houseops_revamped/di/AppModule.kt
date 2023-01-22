package com.example.houseops_revamped.di

import com.example.houseops_revamped.feature_authentication.login.data.repository.LoginRepositoryImpl
import com.example.houseops_revamped.feature_authentication.login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //  FIREBASE
    //  Firestore instance
    @Provides
    @Singleton
    fun provideFirebaseFiresore() = Firebase.firestore

    //  Authentication instance
    @Provides
    @Singleton
    fun provideFirebaseAuthentication() = Firebase.auth

    //  --------------  LOGIN   -----------
    @Provides
    @Singleton
    fun provideLoginRepository(
        auth: FirebaseAuth
    ) : LoginRepository = LoginRepositoryImpl(auth)
}

















